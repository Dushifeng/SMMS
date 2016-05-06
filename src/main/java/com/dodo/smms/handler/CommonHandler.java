package com.dodo.smms.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.smms.common.Auth;
import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.entity.User;
import com.dodo.smms.service.CommonService;

@Controller
public class CommonHandler {
	@Autowired
	CommonService service;
	
	
	@RequestMapping("login")
	public String login(String username,String password,int auth,Map<String,Object> map,HttpSession session){
		System.out.println(username + " "+password+" "+auth);
		if(auth==Auth.ADMIN){
			User user = service.adminLogin(username, password);
			
			if(user!=null){
				session.setAttribute("user", user);
				return "main_admin";
			}
			
		}else if(auth==Auth.SUPERMARKET){
			SuperMarket sm = service.supermarletLogin(username, password);
			if(sm!=null){
				session.setAttribute("sm", sm);
				return "main_supermarket";
			}
			
		}else if(auth==Auth.WAREHOUSE){
			return "";
		}
		
		map.put("errorMessage", "1");
		return "forward:/index.jsp";
		

		
		
		
	}
}
