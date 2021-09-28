package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private int msgid;
    private String content;
    private String messageTypeid;
}
