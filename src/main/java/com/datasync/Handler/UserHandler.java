package com.datasync.Handler;


import com.datasync.Entity.User;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "user")
public class UserHandler implements EntryHandler<User> {

    @Override
    public void insert(User user) {
        System.err.println("insert:"+user);
    }

    @Override
    public void update(User before, User after) {
        System.err.println("before:"+before);
        System.err.println("after:"+after);

    }

    @Override
    public void delete(User user) {

        System.err.println("delete:"+user);
    }

}
