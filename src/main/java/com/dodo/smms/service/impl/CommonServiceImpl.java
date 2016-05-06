package com.dodo.smms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dodo.smms.common.Auth;
import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.entity.User;
import com.dodo.smms.entity.WareHouse;
import com.dodo.smms.repository.SuperMarketRepository;
import com.dodo.smms.repository.UserRepository;
import com.dodo.smms.service.CommonService;
@Component
public class CommonServiceImpl implements CommonService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SuperMarketRepository superMarketRepository;
	
	@Override
	public SuperMarket supermarletLogin(String name, String password) {
		User user = userRepository.findByUsernameAndPasswordAndKind(name, password, Auth.SUPERMARKET);
		SuperMarket sm = superMarketRepository.findOne(user.getPid());
		sm.setCurrentUser(user);
		return sm;
	}

	@Override
	public User adminLogin(String name, String password) {
		User user = userRepository.findByUsernameAndPasswordAndKind(name, password, Auth.ADMIN);
		return user;
	}

	@Override
	public WareHouse wareHouseLogin(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SuperMarket> getALLSM() {
		return superMarketRepository.findAll();
	}

}
