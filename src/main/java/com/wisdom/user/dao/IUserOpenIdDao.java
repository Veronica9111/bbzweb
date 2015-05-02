package com.wisdom.user.dao;

import com.wisdom.common.model.UserOpenid;

/**
 * Created by kcchai on 2015/4/30.
 */
public interface IUserOpenIdDao {

    public UserOpenid getUserOpenidByUserId(String userId);

    public boolean addUserOpenid(String userId, String openId);

}
