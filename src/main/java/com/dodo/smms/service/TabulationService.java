package com.dodo.smms.service;

import java.util.Date;

import com.dodo.smms.entity.Goods;

public interface TabulationService {
	public void makeTabulationByDay(Date from,Date to);
	public void makeTabulationBySupermarket(int sid);
	public void makeTabulationByGoods(Goods goods);
	
}
