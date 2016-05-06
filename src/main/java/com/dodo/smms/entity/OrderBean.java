package com.dodo.smms.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderBean {
	private List<Indent> orders = new ArrayList<>();
	private float[][] result;
	private int id;
	private float[][] map;
	

	public float[][] getMap() {
		return map;
	}
	public void setMap(float[][] map) {
		this.map = map;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Indent> getOrders() {
		return orders;
	}
	public void setOrders(List<Indent> orders) {
		this.orders = orders;
	}
	public float[][] getResult() {
		return result;
	}
	public void setResult(float[][] result) {
		this.result = result;
	}
	
	
	
}
