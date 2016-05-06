package com.dodo.smms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Goods {
	private int id;
	private String barCode;
	private String kind;
	private String name;
	private String no;
	private float price;
	private String unit;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Goods(String barCode, String kind, String name, String no, float price, String unit) {
		super();
		this.barCode = barCode;
		this.kind = kind;
		this.name = name;
		this.no = no;
		this.price = price;
		this.unit = unit;
	}
	public Goods() {
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", barCode=" + barCode + ", kind=" + kind + ", name=" + name + ", no=" + no
				+ ", price=" + price + ", unit=" + unit + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if(obj==null||!(obj instanceof Goods)){
			return false;
		}
		Goods g = (Goods)obj;
		if(g.id==this.id||g.barCode.equals(this.barCode)){
			return true;
		}else{
			return false;
		}
	}
	
}
