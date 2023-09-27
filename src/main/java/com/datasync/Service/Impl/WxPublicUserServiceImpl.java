package com.datasync.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datasync.Entity.WxPublicUser;
import com.datasync.Mapper.WxPublicUserMapper;
import com.datasync.Service.WxPublicUserService;
import org.springframework.stereotype.Service;

@Service
public class WxPublicUserServiceImpl extends ServiceImpl<WxPublicUserMapper, WxPublicUser> implements WxPublicUserService  {

}
