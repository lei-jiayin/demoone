package com.springcloudone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author xw
 * @date 2020/6/24 17:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestString {
    @Test
    public void testString(){
        String a = "";
        char[] b = new char[3];
        b[0] = '1';
        b[1] = '2';
        System.out.println(Arrays.copyOf(b, b.length));
    }

}
