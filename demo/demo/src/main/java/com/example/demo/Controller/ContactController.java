package com.example.demo.Controller;

import com.example.demo.Dto.ContactDTO;
import com.example.demo.Model.Contact;
import com.example.demo.Response.BaseResponse;
import com.example.demo.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.rowset.serial.SerialArray;

public class ContactController {
    @Autowired
    public Service service;

    @PostMapping(value = "/savecontact")
    public BaseResponse saveContact(@RequestBody ContactDTO contactDTO) {
        BaseResponse<Contact>  baseResponse =null;
        baseResponse=BaseResponse.<Contact>builder().Data(service.savecontact(contactDTO)).build() ;
        return baseResponse;
    }
}
