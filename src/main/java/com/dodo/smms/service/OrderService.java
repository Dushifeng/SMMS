package com.dodo.smms.service;


import java.util.List;
import java.util.Map;

import com.dodo.smms.common.AllocationStrategy;
import com.dodo.smms.entity.Goods;
import com.dodo.smms.entity.Indent;
import com.dodo.smms.entity.IndentGroup;
import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.search.Page;
import com.dodo.smms.search.RequestInfo;


public interface OrderService {
	

	public List<IndentGroup> getAllNewOrder();

	public Page<Goods> getAllGoods(RequestInfo info);

	public List<Indent> convertMap(Map<Integer, Integer> map);

	public void submitOrder(List<Indent> order, SuperMarket sm, String desc);

	public List<IndentGroup> getALLMyOrders(SuperMarket sm);

	public List<IndentGroup> getALLOldOrder();

	
	
	
}
