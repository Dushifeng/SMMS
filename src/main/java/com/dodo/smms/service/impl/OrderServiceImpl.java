package com.dodo.smms.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dodo.smms.common.AllocationStrategy;
import com.dodo.smms.entity.Goods;
import com.dodo.smms.entity.Indent;
import com.dodo.smms.entity.IndentGroup;
import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.repository.GoodsRepository;
import com.dodo.smms.repository.IndentGroupRepository;
import com.dodo.smms.repository.IndentRepository;
import com.dodo.smms.search.ELSearch;
import com.dodo.smms.search.Page;
import com.dodo.smms.search.RequestInfo;
import com.dodo.smms.service.OrderService;
@Component
public class OrderServiceImpl implements OrderService{
	@Autowired
	IndentGroupRepository gRepository;
	
	@Autowired
	IndentRepository iRepository;
	
	@Autowired
	GoodsRepository goodsRepository;
	

	@Override
	public List<IndentGroup> getAllNewOrder() {
		
		List<IndentGroup> groups =gRepository.findAll();
		Iterator<IndentGroup> iterator = groups.iterator();
		while(iterator.hasNext()){
			IndentGroup group = iterator.next();
			if(group.getDealedTime()!=null){
				iterator.remove();
			}
		}
	
		
		return groups;
	}

	@Override
	public Page<Goods> getAllGoods(RequestInfo info) {
		
		return  ELSearch.queryGoods(info);
	}

	@Override
	public List<Indent> convertMap(Map<Integer, Integer> map) {
		List<Indent> order = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry:map.entrySet()){
			Goods good = goodsRepository.findOne(entry.getKey());
			order.add(new Indent(null, entry.getValue(), good, good.getPrice()*entry.getValue(), 0,  0));
		}
		 
		return order;
	}

	@Override
	public void submitOrder(List<Indent> order, SuperMarket sm,String desc) {
	
		IndentGroup group = new IndentGroup(new Date(), null, sm );
		group.setDesc(desc);
		group.setOrder(order);
		group = gRepository.saveAndFlush(group);
		group.setProposer(sm.getCurrentUser());
		for(Indent indent:order){
			indent.setGid(group);
			iRepository.save(indent);
		}
	}

	@Override
	public List<IndentGroup> getALLMyOrders(SuperMarket sm) {
	
		return gRepository.findBySm(sm);
	}

	@Override
	public List<IndentGroup> getALLOldOrder() {
		List<IndentGroup> groups =gRepository.findAll();
		Iterator<IndentGroup> iterator = groups.iterator();
		while(iterator.hasNext()){
			IndentGroup group = iterator.next();
			if(group.getDealedTime()==null){
				iterator.remove();
			}
		}
	
		
		return groups;
	}


}
