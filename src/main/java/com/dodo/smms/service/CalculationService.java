package com.dodo.smms.service;

import java.util.List;
import java.util.Map;

import com.dodo.smms.entity.Indent;
import com.dodo.smms.entity.IndentGroup;
import com.dodo.smms.entity.SuperMarket;

public interface CalculationService {
	
	public void dealOrders(List<IndentGroup> groups);
}
