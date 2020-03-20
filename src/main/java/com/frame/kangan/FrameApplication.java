package com.frame.kangan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
//@MapperScan(basePackages = {"com.frame.kangan.data"})
public class FrameApplication {
	public static void main(String[] args) {
		SpringApplication.run(FrameApplication.class, args);
	}
	
	
}
