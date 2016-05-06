package com.dodo.smms.search;


import java.util.List;

public class Page<T> {
	private List<T> content;
	private int size;//每页多少
	private long totalElements;//总记录数
	private int curPageNum;
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getTotalNum() {
		return (int) ((totalElements-1)/size+1);
	}
	
	public long getSize() {
		return size;
	}
	public void setSize(int l) {
		this.size = l;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long l) {
		this.totalElements = l;
	}
	public boolean isFirstPage() {
		return curPageNum==1?true:false;
	}
	
	public boolean isLastPage() {
		if(curPageNum*size<totalElements){
			return false;
		}else{
			return true;
		}
	}
	public int getCurPageNum() {
		return curPageNum;
	}
	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}
	
	
	
}
