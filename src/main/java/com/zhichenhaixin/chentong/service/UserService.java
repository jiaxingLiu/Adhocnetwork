package com.zhichenhaixin.chentong.service;

import org.springframework.stereotype.Service;

import com.zhichenhaixin.chentong.model.User;
import com.zhichenhaixin.chentong.utils.ResourceUtil;

/**
 * 用户服务类
 * 
 * @version V1.0
 * @author pwl
 * @date 2018年6月14日 下午4:00:28
 * @Description
 */
@Service("userService")
public class UserService {
	
	//@Autowired
	//private UserDao userDao;
	
	public User getUser(String username) {
		User user = new User();
		user.setuAccount(ResourceUtil.userName);
		user.setuPwd(ResourceUtil.userPassword);
		return user;
	}
    
	public Boolean checkLogin(String name, String password) {
		/*User user = userDao.selectByAccount(name);
		if (user!=null && password.equals(user.getuPwd())) {
			return true;
		}
		return false;*/
		if(name != null && name.equals(ResourceUtil.userName)
				&& password != null && password.equals(ResourceUtil.userPassword)){
			return true;
		}
		return false;
	}
}
