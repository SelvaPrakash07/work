package com.example.demo.Controller;

import com.example.demo.Dto.ContactDTO;
import com.example.demo.Dto.MessagetypeDTO;
import com.example.demo.Model.Contact;
import com.example.demo.Model.MessageType;
import com.example.demo.Response.BaseResponse;
import com.example.demo.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MessageController {
    @Autowired
    public Service service;

    @PostMapping(value = "/savemessage/type")
    public BaseResponse saveContact(@RequestBody MessagetypeDTO messagetypeDTO) {
        BaseResponse<MessageType>  baseResponse =null;
        baseResponse=BaseResponse.<MessageType>builder().Data(service.saveMessagetype(messagetypeDTO)).build() ;
        return baseResponse;
    }

}
