/**   
* @Title: IUserService.java 
* @Package com.frame.rengu.service
* @Description: TODO(用一句话描述该文件做什么) 
* @author kang.an@ele.me   
* @date 2016年6月22日 下午5:25:20 
* @version V1.0   
*/
package com.frame.rengu.service;

import java.util.Set;

import com.frame.rengu.data.po.FrameUser;


public interface IUserService {
	
	Set<String> getUserRolesByUserId(int userId); 
	
	Set<String> getUserRolesByUserAccount(String account); 
	
	FrameUser getUserByAccountAndPwd(String account,String password);
	
}