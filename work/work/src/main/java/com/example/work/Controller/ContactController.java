package com.example.work.Controller;

import com.example.work.Dto.ContactDTO;
import com.example.work.Dto.OutputDTO;
import com.example.work.Model.Contact;
import com.example.work.Response.BaseResponse;
import com.example.work.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController

public class ContactController {

    @Autowired
    public ContactService contactService;

    @PostMapping(value = "/savecontact")
    public BaseResponse saveContact(@RequestBody ContactDTO contactDTO) {
        BaseResponse<Contact>  baseResponse =null;
        baseResponse=BaseResponse.<Contact>builder().Data(contactService.savecontact(contactDTO)).build() ;
        return baseResponse;
    }
    @GetMapping(value = "/getby/contact")
    public BaseResponse getByContact(@RequestBody OutputDTO outputDTO) {
        BaseResponse<Contact>  baseResponse =null;
        baseResponse=BaseResponse.<Contact>builder().Data(contactService.getbycontact(outputDTO)).build() ;
        return baseResponse;
    }


}
