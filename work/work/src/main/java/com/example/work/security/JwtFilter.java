package com.example.work.security;


import com.example.work.Constants;
import com.example.work.Service.ServiceImpl.ContactImpl;
import com.example.work.Service.ServiceImpl.ContactServiceImpl;
import com.example.work.util.JwtUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/******************************************************************************
 * Author * Modified Date * Version * Description *
 ******************************************************************************
 * Coherent * 08/05/2019 * V1.0 * JwtFilter class * *
 *****************************************************************************/


public class JwtFilter extends OncePerRequestFilter {

    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private ContactServiceImpl contact;

        @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String SIGNING_KEY = "AP-COHERENT";
        try {
            String token = jwtUtil.getJwtFromRequest(request);
            if (StringUtils.hasText(token) && jwtUtil.validateToken(token, SIGNING_KEY)) {
                String username = jwtUtil.getUserIdFromJWT(token, SIGNING_KEY);
                if (Strings.isNullOrEmpty(String.valueOf(username))) {
                    request.getRequestDispatcher("/error?errorCode=0&errorMessage=" + Constants.JWT_USER_MESSAGE)
                            .forward(request, response);
                }
                String authorities = jwtUtil.getUserIdFromJWT(token, SIGNING_KEY);
                UserDetails userDetails = contact.loadByUserName(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        authorities, username, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("/error?errorCode=0&errorMessage=" + ex.getMessage()).forward(request,
                    response);
        }
    }
}
