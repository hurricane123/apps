package com.hurricane.app.cartoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("json")
	@ResponseBody
	public String sayHello() {
		return "jsonHello";
	}

	@RequestMapping("jsp")
	public String jspHello() {
		return "hello";
	}
	
	@RequestMapping("viewCartoon")
	public String viewCartoon() {
		return "main";
	}
	
	@RequestMapping("video")
	public String viewVideo() {
		return "video";
	}
}
