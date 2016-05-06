package com.dodo.smms.handler;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.smms.common.AllocationStrategy;
import com.dodo.smms.entity.Goods;
import com.dodo.smms.entity.Indent;
import com.dodo.smms.entity.IndentGroup;
import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.search.Page;
import com.dodo.smms.search.RequestInfo;
import com.dodo.smms.service.CalculationService;
import com.dodo.smms.service.OrderService;

@Controller
public class OrderHandler {
	@Autowired
	OrderService service;
	
	
	@Autowired
	CalculationService calService;
	
	@RequestMapping("neworder")
	public String showAllNewOrder(HttpSession session){
		
		List<IndentGroup> groups = service.getAllNewOrder();
		session.setAttribute("orders", groups);
		
		return "admin_order_new";
	}
	
	@RequestMapping("applyforgoods")
	public String showAllGoods(Map<String,Object> map,
			@RequestParam(name = "name", required = false,defaultValue="") String name,
			@RequestParam(name = "barcode", required = false,defaultValue="") String barcode,
			@RequestParam(name = "unit", required = false,defaultValue="") String unit,
			@RequestParam(name = "minprice", required = false,defaultValue="0") float minprice,
			@RequestParam(name = "maxprice", required = false,defaultValue=Float.MAX_VALUE+"") float maxprice,
			@RequestParam(name = "size", required = false,defaultValue="5") int size,
			@RequestParam(name = "pno", required = false,defaultValue="1") int pno
			) throws UnsupportedEncodingException{
		RequestInfo info=new RequestInfo(name, barcode, minprice, maxprice, unit, size, (pno-1)*size);
		
		Page<Goods> goods = service.getAllGoods(info);		
		map.put("goods", goods);
		if(!name.equals(""))
		map.put("name",name);
		if(!barcode.equals(""))
		map.put("barcode", barcode);
		if(!unit.equals(""))
		map.put("unit", unit);
		if(minprice!=0)
		map.put("minprice", minprice);
		if(maxprice!=Float.MAX_VALUE)
		map.put("maxprice", maxprice);
		map.put("size", size);
		map.put("pno", pno);
		return "supermarket_goods";
	}
	
	@RequestMapping("addItem")
	@ResponseBody
	public void addItem(int id,int num,HttpSession session){
		Map<Integer,Integer> map;
		map = (Map<Integer, Integer>) session.getAttribute("cart");
		if(map==null){
			map = new HashMap<>();
			session.setAttribute("cart", map);
		}
		map.put(id, num);
	}
	
	@RequestMapping("submitSelect")
	public String submitSelect(HttpSession session){
		Map map = (Map<Integer, Integer>) session.getAttribute("cart");
		List<Indent> order = service.convertMap(map);
		
		session.setAttribute("order", order);
		return "showCart";
	}
	
	@RequestMapping("submitOrder")
	public String submitOrder(HttpSession session,String desc){
		List<Indent> order = (List<Indent>) session.getAttribute("order");
		SuperMarket sm = (SuperMarket) session.getAttribute("sm");
		desc = desc.trim();
		
		service.submitOrder(order,sm,desc);
		session.setAttribute("cart", null);
		session.setAttribute("order", null);
		return "submitSuccess";
	}
	
	
	@RequestMapping("dealOrder")
	public String dealOrder(HttpSession session){
		List<IndentGroup> groups = (List<IndentGroup>) session.getAttribute("orders");
		//Map<Integer,AllocationStrategy> stratepy = new HashMap<Integer, AllocationStrategy>();
		calService.dealOrders(groups);
		session.setAttribute("orders",null);
		return "dealOrderSuccess";
		
	}
	@RequestMapping("showMyOrders")
	public String spShowOrder(Map<String,Object> map,HttpSession session){
		SuperMarket sm = (SuperMarket) session.getAttribute("sm");
		List<IndentGroup> ig = service.getALLMyOrders(sm);
		return "supermarket_orders";
	}
	
	@RequestMapping("admin_showOldOrder")
	public String AdminShowOldOrder(Map<String,Object> map){
		List<IndentGroup> groups = service.getALLOldOrder();
		map.put("orders", groups);
		return "admin_order_old";
	}
}
