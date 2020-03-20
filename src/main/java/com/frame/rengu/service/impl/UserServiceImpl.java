/**   
* @Title: userServiceImpl.java 
* @Package com.frame.rengu.service.impl
* @Description: TODO(用一句话描述该文件做什么) 
* @author kang.an@ele.me   
* @date 2016年6月23日 上午11:00:54 
* @version V1.0   
*/
package com.frame.rengu.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.rengu.data.mapper.FrameUserMapper;
import com.frame.rengu.data.po.FrameUser;
import com.frame.rengu.service.IUserService;


@Service
public class UserServiceImpl implements IUserService{

	
	@Autowired
	private FrameUserMapper frameUserMapper;
	/* (非 Javadoc) 
	* <p>Title: getUserRolesByUserId</p> 
	* <p>Description: </p> 
	* @param userId
	* @return 
	* @see com.frame.rengu.service.IUserService#getUserRolesByUserId(int)
	*/
	@Override
	public Set<String> getUserRolesByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	* <p>Title: getUserRolesByUserAccount</p> 
	* <p>Description: </p> 
	* @param account
	* @return 
	* @see com.frame.rengu.service.IUserService#getUserRolesByUserAccount(java.lang.String)
	*/
	@Override
	public Set<String> getUserRolesByUserAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	* <p>Title: getUserByAccountAndPwd</p> 
	* <p>Description: </p> 
	* @param account
	* @param password
	* @return 
	* @see com.frame.rengu.service.IUserService#getUserByAccountAndPwd(java.lang.String, java.lang.String)
	*/
	@Override
	public FrameUser getUserByAccountAndPwd(String account, String password) {
		return frameUserMapper.getUserByAccount(account, password);
	}

}
