package com.zhichenhaixin.chentong.dao;

import com.zhichenhaixin.chentong.model.User;
/**
 * 用户DAO
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
//@Repository
public interface UserDao {
	
	User selectByAccount(String uAccount);
	
}
