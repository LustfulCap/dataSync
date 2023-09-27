package com.datasync.Handler;


import com.datasync.Dto.WxPublicUserDto;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "wx_public_user")
public class WPUHandler implements EntryHandler<WxPublicUserDto> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Gson gson;

    @Override
    public void insert(WxPublicUserDto wxPublicUserDto) {
        System.err.println("insert:"+wxPublicUserDto);
        rabbitTemplate.convertAndSend("wx_public_user_exchange","insert",gson.toJson(wxPublicUserDto));
    }


    @Override
    public void update(WxPublicUserDto before, WxPublicUserDto after) {
        System.err.println("before:"+before);
        System.err.println("after:"+after);

        rabbitTemplate.convertAndSend("wx_public_user_exchange","update",gson.toJson(after));
    }

    @Override
    public void delete(WxPublicUserDto wxPublicUserDto) {

        System.err.println("delete:"+wxPublicUserDto);

        rabbitTemplate.convertAndSend("wx_public_user_exchange","delete",gson.toJson(wxPublicUserDto));
    }


}
