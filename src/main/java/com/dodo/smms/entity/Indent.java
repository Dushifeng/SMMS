package com.dodo.smms.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table
public class Indent {
	private int id;
	private IndentGroup gid;
	private int needNum;
	private Goods goods;
	private float price;
	private Integer realNum;
	private Map<WareHouse,Integer> house = new HashMap<WareHouse,Integer>();
	private int allocationStrategy;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(targetEntity=IndentGroup.class,fetch=FetchType.LAZY)
	@JoinColumn(name="groupid")
	public IndentGroup getGid() {
		return gid;
	}
	public void setGid(IndentGroup gid) {
		this.gid = gid;
	}
	public int getNeedNum() {
		return needNum;
	}
	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}
	@ManyToOne(targetEntity=Goods.class,fetch=FetchType.EAGER)
	@JoinColumn(name="gid")
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getRealNum() {
		return realNum;
	}
	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
	
	
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="wid")
	@Column(name="num")
	@JoinTable(name="indent_warehouse",joinColumns=@JoinColumn(name="iid"))
	public Map<WareHouse,Integer> getHouse() {
		return house;
	}
	public void setHouse(Map<WareHouse,Integer> house) {
		this.house = house;
	}
	public int getAllocationStrategy() {
		return allocationStrategy;
	}
	public void setAllocationStrategy(int allocationStrategy) {
		this.allocationStrategy = allocationStrategy;
	}
	public Indent(IndentGroup gid, int needNum, Goods goods, float price, Integer realNum, 
			int allocationStrategy) {
		this.gid = gid;
		this.needNum = needNum;
		this.goods = goods;
		this.price = price;
		this.realNum = realNum;
		this.allocationStrategy = allocationStrategy;
	}
	public Indent() {
	}
	@Override
	public String toString() {
		return "Indent [id=" + id + ", gid=" + gid + ", needNum=" + needNum + ", goods=" + goods + ", price=" + price
				+ ", realNum=" + realNum + ", house=" + house + ", allocationStrategy=" + allocationStrategy + "]";
	}
	
}
