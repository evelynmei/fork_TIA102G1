package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	//首頁
	@GetMapping("/")
	public String hello(Model model) {
		return "index";
	}
	
	//促銷活動
	@GetMapping("/event/select_page")
	public String select_page(Model model) {		
		return "event/select_page";
	}
}
