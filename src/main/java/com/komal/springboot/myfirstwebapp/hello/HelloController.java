package com.komal.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	//http://localhost:8080/hello
	@RequestMapping("hello")
	@ResponseBody
	public String sayHello()
	{
		return "Hello World...";
	}
	
	@RequestMapping("helloJsp")
	public String sayHelloJsp()
	{
		return "/WEB-INF/jsp/SayHello.jsp";
	}
}
