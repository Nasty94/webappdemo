package com.demo.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class HelloWorldService{

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

	public String getDesc() {

		logger.debug("getDesc() is executed!");

		return "Web App Demo";

	}

	public String getTitle(String name) {

		logger.debug("getTitle() is executed! $name : {}", name);

		if(StringUtils.isEmpty(name)){
			return "Hello!";
		}else{
			return "Hello " + name;
		}
		
	}
	
	 
	  
}