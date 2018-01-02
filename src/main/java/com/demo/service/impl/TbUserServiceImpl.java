package com.demo.service.impl;

import com.demo.mapper.TbUserMapper;
import com.demo.pojo.TbUser;
import com.demo.pojo.TbUserExample;
import com.demo.service.TbUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Value("${TEST_DATA}")
    private String data;

    @Autowired
    private TbUserMapper tbUserMapper;

    private static Logger log = LoggerFactory.getLogger(TbUserServiceImpl.class);

    public TbUser getUserByPhoneNum(String phoneNum) {

        System.out.print("Service:"+data);

        TbUser user =null;
        try{
            TbUserExample example = new TbUserExample();
            example.createCriteria().andPhoneNumEqualTo("15160000933");
            List<TbUser> list = tbUserMapper.selectByExample(example);
            user =list.get(0);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查找错误!");
        }
        return user;
    }





}
