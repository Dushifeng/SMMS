package com.dodo.smms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Distance {
	private int id;
	private float distance;
	private WareHouse house;
	private SuperMarket market;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	@ManyToOne(targetEntity=WareHouse.class)
	public WareHouse getHouse() {
		return house;
	}
	public void setHouse(WareHouse house) {
		this.house = house;
	}
	@ManyToOne(targetEntity=SuperMarket.class)
	public SuperMarket getMarket() {
		return market;
	}
	public void setMarket(SuperMarket market) {
		this.market = market;
	}
	public Distance(float distance, WareHouse house, SuperMarket market) {
		this.distance = distance;
		this.house = house;
		this.market = market;
	}
	public Distance() {	
	}
	
}
