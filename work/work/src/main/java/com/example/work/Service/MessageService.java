package com.example.work.Service;

import com.example.work.Dto.MessagetypeDTO;
import com.example.work.Model.MessageType;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
   /* MessageType saveMessagetype(MessagetypeDTO messagetypeDTO);*/

    MessageType savetype(MessagetypeDTO messagetypeDTO);
}
