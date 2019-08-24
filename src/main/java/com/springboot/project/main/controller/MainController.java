package com.springboot.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.project.common.utils.HttpRequestHelper;

@Controller
public class MainController {

	@GetMapping("/main/index.do")
	public String index() throws Exception {
		return HttpRequestHelper.getJspPath();
	}
}
