package com.datasync.Handler;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class consumerHandler {

    @Autowired
    private RedisTemplate<String,Object> redisTemplates;


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "wx_public_user_exchange",type = "direct"),
                    key = {"insert"}
            )
    })
    public void insertConsumer(String message){
        System.out.println("insert 消费者："+message);
        BoundListOperations<String, Object> wxPublicUser = redisTemplates.boundListOps("wx_public_user");
        wxPublicUser.leftPush(message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "wx_public_user_exchange",type = "direct"),
                    key = {"update"}
            )
    })
    public void updateConsumer(String message){
        System.out.println("update 消费者:"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "wx_public_user_exchange",type = "direct"),
                    key = {"delete"}
            )
    })
    public void deleteConsumer(String message){
        System.out.println("delete 消费者:"+message);
    }


}