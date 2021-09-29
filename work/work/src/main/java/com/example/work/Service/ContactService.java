package com.example.work.Service;


import com.example.work.Dto.ContactDTO;
import com.example.work.Dto.OutputDTO;
import com.example.work.Model.Contact;

@org.springframework.stereotype.Service
public interface ContactService {

    Contact getbycontact(OutputDTO outputDTO);

    Contact savecontact(ContactDTO contactDTO);
}
