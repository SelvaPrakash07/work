package com.example.firstapp.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewDTO {
    private String username;
    private List<RoleDto> roles;
}
