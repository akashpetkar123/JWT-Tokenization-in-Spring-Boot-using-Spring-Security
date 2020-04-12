package com.akash.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.akash.demo.reqres.AuthenticationRequest;
import com.google.gson.Gson;

@SpringBootApplication
public class DemoApplication {

	final static Logger log=LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		log.info("DemoApplication got Started......");
	}

}
