package com.dodo.smms.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.service.CommonService;

@Controller
public class OtherHandler {
	@Autowired
	CommonService comService;
	
	@RequestMapping("admin_showAllSM")
	public String showAllSM(Map<String,Object> map){
		List<SuperMarket> sms = comService.getALLSM();
		map.put("sms", sms);
		return "admin_showSM";
	}
	
	@RequestMapping("newOrUpdateSM")
	public void addNewSM(String name,String address,String man,String phone,String no,ArrayList<Integer> wh,ArrayList<Float> dis){
		
		System.out.println("name"+name+" "+address+" "+man+" "+phone+" "+no+" "+wh+" "+dis);
	}
}
