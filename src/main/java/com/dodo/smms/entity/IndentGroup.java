package com.dodo.smms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table
public class IndentGroup {
	private int id;
	private List<Indent> order = new ArrayList<>();
	private Date createTime;
	private Date dealedTime;
	private SuperMarket sm;
	private String desc;
	
	private User proposer;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToMany(targetEntity=Indent.class,mappedBy="gid")
	public List<Indent> getOrder() {
		return order;
	}
	public void setOrder(List<Indent> order) {
		this.order = order;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDealedTime() {
		return dealedTime;
	}
	public void setDealedTime(Date dealedTime) {
		this.dealedTime = dealedTime;
	}
	
	@ManyToOne(targetEntity=SuperMarket.class)
	@JoinColumn(name="sid")
	public SuperMarket getSm() {
		return sm;
	}
	public void setSm(SuperMarket sm) {
		this.sm = sm;
	}


	public IndentGroup( Date createTime, Date dealedTime, SuperMarket sm) {
		this.createTime = createTime;
		this.dealedTime = dealedTime;
		this.sm = sm;
	}
	
	public IndentGroup() {
	}
	
	@Column(name="remark")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="uid")
	public User getProposer() {
		return proposer;
	}
	public void setProposer(User proposer) {
		this.proposer = proposer;
	}
	@Override
	public String toString() {
		return "IndentGroup [id=" + id  + ", createTime=" + createTime + ", dealedTime="
				+ dealedTime + ", sm=" + sm + ", desc=" + desc + ", proposer=" + proposer + "]";
	}
	
	
}
