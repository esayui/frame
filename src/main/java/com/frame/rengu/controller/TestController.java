/**   
* @Title: TestController.java 
* @Package com.frame.rengu.controller
* @Description: TODO(用一句话描述该文件做什么) 
* @author kang.an@ele.me   
* @date 2016年5月31日 下午3:24:27 
* @version V1.0   
*/
package com.frame.rengu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.rengu.config.RabbitMQConfig;
import com.frame.rengu.data.mapper.TYPE_J_CONTROLMapper;
import com.frame.rengu.data.po.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.frame.rengu.data.mapper.FrameImageMapper;
import com.frame.rengu.data.mapper.FrameUserMapper;
import com.frame.rengu.data.mapper.FrameUserPermissionMapper;
import com.google.gson.Gson;


@RestController
@EnableCaching
public class TestController {
	
    private static final Log logger = LogFactory.getLog(TestController.class);
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private FrameImageMapper frameImageMapper;
	
	@Autowired
	private FrameUserMapper frameUserMapper;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@Autowired
	private TYPE_J_CONTROLMapper type_j_controlMapper;
	
	@Autowired
	private FrameUserPermissionMapper frameUserPermissionMapper;


	
	
	@RequestMapping("/test")
	@ResponseBody
	public String testRedis(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		redisTemplate.opsForValue().set(list, list);
		Gson g = new Gson();
		return g.toJson(redisTemplate.opsForValue().get(456456));
	}
	
	
	@RequestMapping("/test2")
	@ResponseBody
	@Cacheable(value = "cache",key="'hello'")  
	public String testRedis2(){
		String a ="1231ee";
		 System.out.println("无缓存的时候调用这里");  
		return a.length()+"";
	}
	
	@RequestMapping("/test3")
	@ResponseBody
	public String testMybatis(){
		Gson g = new Gson();
//		return g.toJson(frameImageMapper.selectTest());
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getPrincipal());
		return g.toJson(frameImageMapper.selectById((long)1));
	}
	
	@RequestMapping("/test4")
	@ResponseBody
	public void testIndex(){
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public void login(HttpServletRequest request,HttpServletResponse res) throws Exception{
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String error = null;
//		Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
//		try {
//            subject.login(token);
//        } catch (UnknownAccountException e) {
//            error = "用户名/密码错误";
//        } catch (IncorrectCredentialsException e) {
//            error = "用户名/密码错误";
//        } catch (AuthenticationException e) {
//            //其他错误，比如锁定，如果想单独处理请单独catch处理
//            error = "其他错误：" + e.getMessage();
//        }
//
//        if(error != null) {//出错了，返回登录页面
//        } else {//登录成功
        	Cookie cookie = new Cookie("account",account);
    		res.addCookie(cookie);
    		res.sendRedirect("/index");
//        }
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpServletRequest request,HttpServletResponse res) throws Exception{
		logger.debug("logout");
		SecurityUtils.getSubject().logout(); 
		res.sendRedirect("/userLogin.html");
	}
	
	
	@RequestMapping("/index")
	@ResponseBody
	public void index(String account,String password){
		logger.debug("woshi log");
		redisTemplate.opsForSet().add("ank","123","4456");
		redisTemplate.opsForSet().add("kang", "123","2222");
		Set<Object> set = redisTemplate.opsForSet().intersect("ank", "kang");
		FrameUser user = frameUserMapper.getUserByAccount("ankang","ankang2");
		System.out.println("123123123");
	}
	

	@RequestMapping("/sendEng")
	public void sendEng(HttpServletRequest httpServletRequest){
		TYPE_J_ENG type_j_eng = new TYPE_J_ENG();
		String electricValue  =httpServletRequest.getParameter("electric");
		type_j_eng.setElectric(Integer.parseInt(electricValue));

		rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "key.#eng",type_j_eng);


	}

	@RequestMapping("/sendControl")
	public void sendControl(HttpServletRequest httpServletRequest){

		TYPE_J_CONTROL type_j_control = new TYPE_J_CONTROL();
		String ControlValue  =httpServletRequest.getParameter("Control");
		type_j_control.setControl(Integer.parseInt(ControlValue));
		type_j_controlMapper.insert(type_j_control);

		rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "key.#control",type_j_control);


	}

	@RequestMapping("/sendMov")
	public void sendMov(HttpServletRequest httpServletRequest){
		String turnValue = httpServletRequest.getParameter("turn");
		TYPE_J_MOV type_j_mov = new TYPE_J_MOV();
		type_j_mov.setTurn(Integer.parseInt(turnValue));
		rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "key.#mov",type_j_mov);

	}

	@RequestMapping("/sendPos")
	public void sendPos(HttpServletRequest httpServletRequest){
		TYPE_J_POS type_j_pos = new TYPE_J_POS();
		String x = httpServletRequest.getParameter("X");
		type_j_pos.setX(x);
		String y = httpServletRequest.getParameter("Y");
		type_j_pos.setY(y);
		String fvm = httpServletRequest.getParameter("fvm");
		type_j_pos.setFvm(fvm);


		rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "key.#pos",type_j_pos);

	}
	
	@RequestMapping("/testPermission")
	@ResponseBody
	public void testPermission(){
		System.out.println(frameUserPermissionMapper.getPermissionCodeSetByUserId(1));
	}
	
}
