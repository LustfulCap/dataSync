package com.datasync.Controller;

import com.alibaba.google.common.reflect.TypeToken;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datasync.Dto.Resp;
import com.datasync.Dto.WxPublicUserDto;
import com.datasync.Entity.WxPublicUser;
import com.datasync.Service.WxPublicUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class WxPublicUserController {

    @Autowired
    private WxPublicUserService wxPublicUserService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private Gson gson;

    @PostMapping("addWPU")
    public String add(@RequestBody WxPublicUser wPU){

        wPU.setId(UUID.randomUUID().toString());
        wPU.setCreateTime(new Date());
        wPU.setDeleteTime(null);
        wPU.setIsDelete(0);
        wPU.setIsFollow(0);



        boolean save = wxPublicUserService.save(wPU);
        if(save) return gson.toJson(new Resp("0","添加成功",null));
        return gson.toJson(new Resp("1","添加失败",null));
    }


    @GetMapping("getData/{pageIndex}/{pageSize}")
    public String getData(@PathVariable int pageIndex,@PathVariable int pageSize){

        List<Object> wxPublicUser = redisTemplate.opsForList().range("wx_public_user", pageIndex, pageSize);


        List<WxPublicUserDto> list = gson.fromJson(wxPublicUser.toString(), new TypeToken<List<WxPublicUserDto>>() {
        }.getType());


        return gson.toJson(new Resp("0","查询成功",list));
    }

    @GetMapping("getData/{size}")
    public String getData(@PathVariable int size){
        LambdaQueryWrapper<WxPublicUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WxPublicUser::getIsDelete,0);
        queryWrapper.last("limit "+size);

        List<WxPublicUser> list = wxPublicUserService.list(queryWrapper);
        list.forEach(item ->{
            System.out.println(item.getCreateTime());
        });


        return gson.toJson(new Resp("0","查询成功",list));
    }


}
