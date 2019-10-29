package com.springcloudone.service;

import com.springcloudone.dao.WxLoginDao;
import com.springcloudone.entity.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xw
 * @date 2019/10/29 14:35
 */
@Service
@Transactional(readOnly = false)
public class WxLoginService {

    @Autowired
    private WxLoginDao dao;

    /**
     * 插入数据库
     * @param wxUser
     */
    public void insert(WxUser wxUser) {
        dao.setUTF();
        dao.insert(wxUser);
    }

    public WxUser get(int i) {
        return dao.get(i);
    }
}
