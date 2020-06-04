package com.springcloudone.redis;

import com.springcloudone.entity.WxUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xw
 * @date 2020/6/3 18:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_2 {

    @Autowired
    private RedisTemplate<String, WxUser> redisTemplate;
    private WxUser d;

    @Before
    public void before() {
        d = new WxUser();
        d.setId(21);
        d.setNickName("xiaoqi");
    }

    @Test
    public void testSet() {
        this.redisTemplate.opsForValue().set("days", d);
        System.out.println((redisTemplate.opsForValue().get("days")));
    }
}

