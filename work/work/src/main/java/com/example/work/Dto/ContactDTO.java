package com.example.work.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ContactDTO {
    private int id;
    private String username;
    private String mobileNo;
    private String mail_id;
    private List<MessageDTO> messageDTOList;
}
