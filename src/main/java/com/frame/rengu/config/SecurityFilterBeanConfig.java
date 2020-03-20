/**   
 * @Title: SecurityFilterBeanConfig.java 
 * @Package com.frame.rengu.config
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author kang.an@ele.me   
 * @date 2016年6月13日 下午1:54:24 
 * @version V1.0   
 */
package com.frame.rengu.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.frame.rengu.web.security.FrameAuthenticatingFilter;
import com.frame.rengu.web.security.FrameRealm;
import com.frame.rengu.web.security.FrameRetryLimitHashedCredentialsMatcher;

/**
 * @ClassName: SecurityFilterBeanConfig
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author kang.an@ele.me
 * 
 * @date 2016年6月13日 下午1:54:24
 * 
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SecurityFilterBeanConfig {

	@Bean
	public FilterRegistrationBean delegatingFilterProxy() throws Exception {
		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
		filterProxy.setTargetFilterLifecycle(true);
		filterProxy.setTargetBeanName("shiroFilter");
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(filterProxy);
		return bean;
	}

	@Bean(name = "shiroFilter")
	public AbstractShiroFilter shiroFilter(SecurityManager securityManager)
			throws Exception {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		FrameAuthenticatingFilter frameAuthenticatingFilter = new FrameAuthenticatingFilter();
		frameAuthenticatingFilter.setLoginUrl("/userLogin.html");
		shiroFilter.setLoginUrl("/userLogin.html");
		shiroFilter.setSuccessUrl("/index");
		// shiroFilter.setUnauthorizedUrl("/forbidden");

		Map<String, Filter> filters = shiroFilter.getFilters();
		filters.put(DefaultFilter.authc.name(), frameAuthenticatingFilter);// 鉴权
		// filters.put("anon", new AnonymousFilter());//游客
		// filters.put("logout", new LogoutFilter());
		// filters.put("roles", new RolesAuthorizationFilter());//角色
		// filters.put("user", new UserFilter());//用户
		filters.put(DefaultFilter.perms.name(),
				new HttpMethodPermissionFilter());

		Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
		try {
			InputStream inputStream = getClass().getResourceAsStream(
					"/pathPermission.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			for (Object name : prop.keySet().toArray()) {
				filterChainDefinitionMapping.put(name.toString(),
						prop.getProperty(name.toString()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);

		return (AbstractShiroFilter) shiroFilter.getObject();
	}

	@Bean(name = "securityManager")
	public SecurityManager securityManager(FrameRealm frameRealm, EhCacheManager cacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(frameRealm);
		securityManager.setCacheManager(cacheManager);
		return securityManager;
	}

	@Bean(name = "frameRealm")
	public FrameRealm frameRealm(EhCacheManager ehCacheManager) {
		FrameRealm realm = new FrameRealm();
		realm.setCredentialsMatcher(credentialsMatcher(ehCacheManager));
		return realm;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean(name = "ehCacheManager")
	public EhCacheManager ehCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return cacheManager;
	}
	
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher(EhCacheManager ehCacheManager){
		FrameRetryLimitHashedCredentialsMatcher credentialsMatcher = new FrameRetryLimitHashedCredentialsMatcher(ehCacheManager);
		credentialsMatcher.setHashAlgorithmName("md5");
		credentialsMatcher.setHashIterations(2);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}

}
