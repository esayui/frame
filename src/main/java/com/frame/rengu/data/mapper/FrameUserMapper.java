package com.frame.rengu.data.mapper;

import com.frame.rengu.data.po.FrameUser;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * FrameUser 表数据库控制层接口
 *
 */
public interface FrameUserMapper extends AutoMapper<FrameUser> {

		FrameUser getUserByAccount(@Param("account")String account,@Param("password")String password);
		
		int  getUserIdByAccount(String account);
}