package com.example.firstapp.Service;

import com.example.firstapp.Dto.UserDto;
import com.example.firstapp.Dto.UserSignupRequestVO;
import com.example.firstapp.Model.Role;
import com.example.firstapp.Model.User;
import com.example.firstapp.Model.UserRole;
import com.example.firstapp.Repository.RoleRepository;
import com.example.firstapp.Repository.UserRepository;
import com.example.firstapp.Repository.UserRoleRepository;
import com.example.firstapp.response.BaseResponse;
import com.example.firstapp.util.Constants;
import com.example.firstapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void Saveuser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUsername());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(userDto.getPassword()));
        List<Role> roleList=new LinkedList<>();
        Role role=new Role();
        role.setRoleName(userDto.getRoleName());
        roleList.add(role);
        user.setRoleList(roleList);
        userRepository.save(user);
    }
    /*private void saveRole(List<RoleDto> roles, User userDetail) {
        try {
            List<UserRole> userRoles = new LinkedList<>();
            if (Objects.nonNull(roles) && roles.size() > 0) {
                roles.stream().forEachOrdered(role -> {
                    Role role1 = roleRepository.findById(role.getId())
                            .orElseThrow(() -> new RuntimeException("role is not here"));
                    UserRole userRole = new UserRole();
                    userRole.setUser(userDetail);
                    userRole.setRole(role1);
                    userRoles.add(userRole);
                });
                userRoleRepository.saveAll(userRoles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


@Override
public BaseResponse logOfUser(UserSignupRequestVO userSignupRequestVO) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        List<Role> roles = new LinkedList<>();
        try {
            Optional<User> user = userRepository.findByUserName(userSignupRequestVO.getUserName());
            boolean status = bCrypt.matches(userSignupRequestVO.getPassword(), user.get().getPassword());
            if (user.isPresent() && status == true) {
                List<UserRole> userRoles = userRoleRepository.findByUserId(user.get().getId());
                userRoles.stream().forEachOrdered(userRole -> {
                    Role role = userRole.getRole();
                    roles.add(role);
                 });
                String Token = JwtUtil.generateToken(Constants.SIGNIN_KEY, user.get().getId(), "user", user.get().getUserName(), roles);
                userSignupRequestVO.setUserName(user.get().getUserName());
                userSignupRequestVO.setId(user.get().getId());
                userSignupRequestVO.setRoleList(user.get().getRoles());
                userSignupRequestVO.setJwtToken(Token);
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusMessage("success");
        baseResponse.setStatusCode("200");
        baseResponse.setData(userSignupRequestVO);
        return baseResponse;
    }

    public UserDetails loadByUserName(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUserName(username);
        List<Role> roles = new LinkedList<>();
        if (userDetail == null) {
            throw new UsernameNotFoundException("user not found");}
        else{
            List<UserRole> userRoles = userRoleRepository.findByUserId(userDetail.get().getId());
            userRoles.stream().forEachOrdered(userRole -> {
                Role role = userRole.getRole();
                roles.add(role);
    });
            return new org.springframework.security.core.userdetails.User(userDetail.get().getUserName(), userDetail.get().getPassword(), getAuthority(userDetail.get().getRoles()));
        }
    }
    private List getAuthority(List<UserRole> role){
        List authorities=new ArrayList();
        role.stream().forEachOrdered(role1 -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" +role1.getRole().getRoleName()));
        });
        return authorities;
    }
}

