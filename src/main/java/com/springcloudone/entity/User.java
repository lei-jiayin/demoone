package com.springcloudone.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xw
 * @date 2020/6/4 11:17
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String name;
}
