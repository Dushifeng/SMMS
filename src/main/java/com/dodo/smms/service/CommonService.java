package com.dodo.smms.service;

import java.util.List;

import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.entity.User;
import com.dodo.smms.entity.WareHouse;

public interface CommonService {
	public SuperMarket supermarletLogin(String name,String password);
	public User adminLogin(String name,String password);
	public WareHouse wareHouseLogin(String name,String password);
	public List<SuperMarket> getALLSM();
}
