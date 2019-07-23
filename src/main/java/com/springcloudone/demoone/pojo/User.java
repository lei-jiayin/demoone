package com.springcloudone.demoone.pojo;

import lombok.Data;
import lombok.ToString;


/**
 * 测试http 请求
 * @author xw
 * @date 2019/5/22 17:04
 */
@Data
@ToString
public class User {
    private Integer id;
    private String userName;
    private String password;

/*    private String email;
    private String phoneNum;
    private Integer status;

    private String note;*/
}
