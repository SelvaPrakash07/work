package com.example.firstapp.Model;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

   /*(mappedBy = "role")
    private List<UserRole> roles;*/
    @OneToMany(fetch = FetchType.LAZY,
                cascade = {
                        CascadeType.ALL
                },mappedBy = "roleList")
    private List<Role> roleList;

}

