package com.example.work.Service.ServiceImpl;

import com.example.work.Constant.MasterConstants;
import com.example.work.Dto.ContactDTO;
import com.example.work.Dto.MessageDTO;
import com.example.work.Dto.OutputDTO;
import com.example.work.Model.Contact;
import com.example.work.Model.ContactMessage;
import com.example.work.Model.Message;
import com.example.work.Repository.ContactMessageRepo;
import com.example.work.Repository.ContactRepository;
import com.example.work.Repository.MessageRepo;
import com.example.work.Service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ContactServiceImpl implements ContactService {
@Autowired
public ContactRepository contactRepo;

@Autowired
public MessageRepo messageRepo;

@Autowired
public ContactMessageRepo contact_messageRepo;

    @Override
    public Contact savecontact(ContactDTO contactDTO) {
        try {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String string = bcrypt.encode(contactDTO.getMobileNo());
            String string2 = bcrypt.encode(contactDTO.getMail_id());
            Contact contact = new Contact();
            contact.setUsername(contactDTO.getUsername());
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
    public Contact getbycontact(OutputDTO outputDTO) {
        try {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            Optional<Contact> contact = contactRepo.findByUsername(outputDTO.getUsername());
            Boolean obj = bcrypt.matches(outputDTO.getMobileNo(), contact.get().getMobileNo());
            if (contact.isPresent() && obj == true) {
                List<MessageDTO> messageDTOList = new LinkedList<>();
                ModelMapper modelMapper = new ModelMapper();
                    contact.stream().forEachOrdered(contact1 -> {
                        MessageDTO messageDTO = modelMapper.map(contact1, MessageDTO.class);
                        messageDTOList.add(messageDTO);
                        outputDTO.setObject(messageDTOList);
                    });
                }else throw new RuntimeException(MasterConstants.ERROR_MESSAGE3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public UserDetails loadByUserName(String username) {
        Optional<Contact> userDetail = contactRepo.findById(Integer.valueOf(username));
            Optional<Contact> opt = Optional.ofNullable(userDetail).orElseThrow(
                            () -> new RuntimeException())
                    .map(ContactImpl::new);
            if (opt.isPresent()) {
                return (UserDetails) opt.get();
            }
            return null;
        }
    }


