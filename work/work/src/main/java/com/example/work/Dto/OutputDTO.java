package com.example.work.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OutputDTO {
    private String username;
    private String mobileNo;
    private int msgid;
    private String content;
    private String messageTypeid;
    private String name;
    public List<MessageDTO> object;
}
