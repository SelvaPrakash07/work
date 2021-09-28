package com.example.demo.Service;

import com.example.demo.Dto.ContactDTO;
import com.example.demo.Dto.MessagetypeDTO;
import com.example.demo.Model.Contact;
import com.example.demo.Model.MessageType;

public interface Service {


    Contact savecontact(ContactDTO contactDTO);

    MessageType saveMessagetype(MessagetypeDTO messagetypeDTO);
}
