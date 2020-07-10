package com.springcloudone.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xw
 * @date 2020/6/4 11:17
 */
@Data
@ToString
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
}
