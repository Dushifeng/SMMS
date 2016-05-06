package com.dodo.smms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class SuperMarket {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String linkman;
	private String no;
	private User currentUser;
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
	public SuperMarket(String name, String address, String phone, String linkman, String no) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.linkman = linkman;
		this.no = no;
	}
	public SuperMarket() {
	}
	@Transient
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	@Override
	public String toString() {
		return "SuperMarket [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", linkman="
				+ linkman + ", no=" + no + "]";
	}
	
}
