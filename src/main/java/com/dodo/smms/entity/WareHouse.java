package com.dodo.smms.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table
public class WareHouse {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String linkman;
	private String no;
	//private List<Indent> indents = new ArrayList<>();
	private Map<Goods,Integer> goods_num = new HashMap<Goods,Integer>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="warehouse_goods", joinColumns={@JoinColumn(name="id")})
	@MapKeyColumn(name="gid")
	@Column(name="num")
	public Map<Goods, Integer> getGoods_num() {
		return goods_num;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setGoods_num(Map<Goods, Integer> goods_num) {
		this.goods_num = goods_num;
	}

	public WareHouse(String name, String address, String phone, String linkman, String no){
		this.address = address;
		this.phone = phone;
		this.linkman = linkman;
		this.no = no;
	}
	
	public WareHouse() {
	}
	
	
}
