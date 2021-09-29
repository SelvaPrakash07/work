package com.example.work.Service.ServiceImpl;


import com.example.work.Dto.MessagetypeDTO;
import com.example.work.Model.MessageType;
import com.example.work.Repository.MessageTypeRepo;
import com.example.work.Service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl implements MessageService {

    @Autowired
    public MessageTypeRepo messageTypeRepo;

    @Override
    public MessageType savetype(MessagetypeDTO messagetypeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        MessageType messageType = modelMapper.map(messagetypeDTO, MessageType.class);
        messageTypeRepo.save(messageType);
        return messageType;
    }

 /*   @Override
    public MessageType saveMessagetype(MessagetypeDTO messagetypeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        MessageType messageType = modelMapper.map(messagetypeDTO, MessageType.class);
        messageTypeRepo.save(messageType);
        return messageType;
    }*/
}
