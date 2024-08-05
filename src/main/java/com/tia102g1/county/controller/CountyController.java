package com.tia102g1.county.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tia102g1.county.model.CountyService;
import com.tia102g1.county.model.CountyVO;

@RestController
public class CountyController {

	@Autowired
	CountyService countySvc;
	
	
    @GetMapping("/counties")
    public String select(@RequestParam Integer cntCode){
    	
    	CountyVO countyVO = countySvc.getOneCounty(cntCode);
    	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); //排除沒貼的欄位
    	String jsonStr = gson.toJson(countyVO);
    	System.out.println(jsonStr);

    	return jsonStr;
    }
	
	
}
