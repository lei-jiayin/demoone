package com.springcloudone.demoone;

import com.springcloudone.component.MathComponent;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author xw
 * @date 2019/7/29 11:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Data
public class TestReflect {

    @Autowired
    MathComponent mc;

    @Test
    public void testAdd() {
        try{
            System.out.println("取到的地址：" + mc.getMathAdd());
            Class claz = Class.forName(mc.getMathAdd());
            Method[] methods = claz.getMethods();
            for (Method method:methods) {
                System.out.println(method);
            }
            Method add = claz.getMethod("add", int.class, int.class);
            Constructor constructor = claz.getConstructor();
            Object o = constructor.newInstance();
            int re = (int) add.invoke(o, 4, 5);
            System.out.println("加法答案：" +  re);

            Method sub = claz.getMethod("sub", int.class, int.class);
            Integer sunr = (Integer) sub.invoke(o, 9, 4);
            System.out.println("减法答案：" + sunr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
