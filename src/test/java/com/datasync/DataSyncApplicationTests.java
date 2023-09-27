package com.datasync;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datasync.Entity.WxPublicUser;
import com.datasync.Service.WxPublicUserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@SpringBootTest
class DataSyncApplicationTests {

    @Autowired
    private WxPublicUserService wxPublicUserService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private Gson gson;

    @Test
    void contextLoads() {

        WxPublicUser wxPublicUser = new WxPublicUser();
        wxPublicUser.setId(UUID.randomUUID().toString());
        wxPublicUser.setWxOpenId(UUID.randomUUID().toString());
        wxPublicUser.setCreateTime(new Date());
        wxPublicUser.setDeleteTime(null);
        wxPublicUser.setAccount("18687197940");
        wxPublicUser.setIsDelete(0);
        wxPublicUser.setIsFollow(0);
        wxPublicUser.setVillagesId("8d23cb0d-b39e-47f3-9200-a354da813f5e");
        wxPublicUser.setPlatNumber("京A00000");
        wxPublicUser.setCompanyId("8928c555-e15b-42f9-919b-77b630ef455c");
        wxPublicUser.setUnionId("oFNpX6z-3aYrDWcPk8V7xIt8zqtI");
        wxPublicUser.setCity("红河");
        wxPublicUser.setPlatNumColour("蓝色");

        BoundListOperations<String, Object> wxPublicUser1 = redisTemplate.boundListOps("wx_public_user");

        for (int i=0;i<100;i++){
            wxPublicUser1.leftPush(wxPublicUser);
        }


    }

    @Test
    void getWxData(){
        LambdaQueryWrapper<WxPublicUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxPublicUser::getIsDelete,0);
        queryWrapper.last("limit 10");

        List<WxPublicUser> list = wxPublicUserService.list(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    void RedisTest(){

//        List<Object> list = redisTemplate.opsForList().range("wx_public_user", 0, 1);
//
//        assert list != null;
//        List<WxPublicUserDto> list1 = new ArrayList<>();
//        list.forEach(item ->{
//
//            list1.add((WxPublicUserDto)item);
//
//        });
//        list1.forEach(item ->{
//            System.out.println(item.getId());
//        });


        List<Object> wxPublicUser = redisTemplate.opsForList().range("wx_public_user", 0, 200);

        assert wxPublicUser != null;
        Object o = wxPublicUser.get(0);
        System.out.println(o.toString());

        Object o1 = wxPublicUser.get(1);
        System.out.println(o1.toString());

//        if(!wxPublicUser.isEmpty()){
//            List<WxPublicUserDto> list = gson.fromJson(wxPublicUser.toString(),new TypeToken<List<WxPublicUserDto>>(){}.getType());
//            list.forEach(item ->{
//                System.out.println(item.getId());
//            });
//        }

    }


}
