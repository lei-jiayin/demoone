package com.springcloudone.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xw
 * @date 2019/7/29 11:49
 */
//@Configuration
@Data
@Component
public class MathComponent {

    @Value("${xw.math_add}")
    private String mathAdd;

    @Value("${xw.server.port}")
    private Integer port;

    @Value("${xw.name}")
    private String namg;

    @Value("${xw.pwd}")
    private String pwd;

}
