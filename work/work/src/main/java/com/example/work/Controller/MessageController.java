package com.example.work.Controller;

import com.example.work.Dto.MessagetypeDTO;
import com.example.work.Model.MessageType;
import com.example.work.Response.BaseResponse;
import com.example.work.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MessageController {

    @Autowired
    public MessageService messageService;

  /*  @PostMapping(value = "/savetype")
    public BaseResponse saveContact(@RequestBody MessagetypeDTO messagetypeDTO) {
        BaseResponse<MessageType>  baseResponse =null;
        baseResponse=BaseResponse.<MessageType>builder().Data(messageService.saveMessagetype(messagetypeDTO)).build() ;
        return baseResponse;
    }*/
  @PostMapping(value = "/savetype")
  public BaseResponse saveType(@RequestBody MessagetypeDTO messagetypeDTO) {
      BaseResponse<MessageType> baseResponse = null;
      baseResponse = BaseResponse.<MessageType>builder().Data(messageService.savetype(messagetypeDTO)).build();
      return baseResponse;
  }
}
