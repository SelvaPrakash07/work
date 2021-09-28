package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ContactDTO {
    private int id;
    private String name;
    private String mobileNo;
    private String mail_id;
    private List<MessageDTO> messageDTOList;
}
