package com.springcloudone.dao;

import com.springcloudone.entity.WxUser;
import org.springframework.stereotype.Repository;

@Repository
public interface WxLoginDao {
    void insert(WxUser wxUser);
    void setUTF();

    WxUser get(int id);
}
