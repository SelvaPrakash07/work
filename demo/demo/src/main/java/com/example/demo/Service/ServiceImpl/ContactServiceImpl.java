package com.example.demo.Service.ServiceImpl;

import com.example.demo.Dto.ContactDTO;
import com.example.demo.Dto.MessageDTO;
import com.example.demo.Dto.MessagetypeDTO;
import com.example.demo.Model.Contact;
import com.example.demo.Model.ContactMessage;
import com.example.demo.Model.Message;
import com.example.demo.Model.MessageType;
import com.example.demo.Repository.ContactRepo;
import com.example.demo.Repository.ContactMessageRepo;
import com.example.demo.Repository.MessageRepo;
import com.example.demo.Repository.MessageTypeRepo;
import com.example.demo.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ContactServiceImpl implements Service {
@Autowired
public ContactRepo contactRepo;

@Autowired
public MessageRepo messageRepo;

@Autowired
public MessageTypeRepo messageTypeRepo;

@Autowired
public ContactMessageRepo contact_messageRepo;

    @Override
    public Contact savecontact(ContactDTO contactDTO) {
        try {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String string = bcrypt.encode(contactDTO.getMobileNo());
            String string2 = bcrypt.encode(contactDTO.getMail_id());
            Contact contact = new Contact();
            contact.setName(contactDTO.getName());
            contact.setMobileNo(string);
            contact.setMail_id(string2);
            contact = contactRepo.save(contact);
                savecontactmessage(contactDTO.getMessageDTOList(), contact);
                return contact;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public MessageType saveMessagetype(MessagetypeDTO messagetypeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        MessageType messageType = modelMapper.map(messagetypeDTO, MessageType.class);
        messageTypeRepo.save(messageType);
        return messageType;
    }

    private void savecontactmessage(List<MessageDTO> messageDTOList, Contact contact) {
        try {
            List<ContactMessage> contactMessageLinkedList = new LinkedList<>();
            if (Objects.nonNull(messageDTOList) && messageDTOList.size() > 0) {
                messageDTOList.stream().forEachOrdered(messages -> {
                    Message message1 = messageRepo.findById(messages.getMsgid())
                            .orElseThrow(() -> new RuntimeException("ID NOT FOUND"));
                    ContactMessage contactMessage = new ContactMessage();
                    contactMessage.setContactid(contact);
                    contactMessage.setMessageid(message1);
                    contactMessageLinkedList.add(contactMessage);
                });
                contact_messageRepo.saveAll(contactMessageLinkedList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


