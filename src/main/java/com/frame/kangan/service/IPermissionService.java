/**   
* @Title: IPermissionService.java 
* @Package com.frame.kangan.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author kang.an@ele.me   
* @date 2016年6月22日 下午5:25:11 
* @version V1.0   
*/
package com.frame.kangan.service;

import java.util.Set;


public interface IPermissionService {
	
	Set<String> getUserPermissionCodeByUserId(int userId);

	Set<String> getUserPermissionCodeByUserAccount(String account);
}
