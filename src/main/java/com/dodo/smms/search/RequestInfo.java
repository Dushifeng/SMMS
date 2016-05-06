package com.dodo.smms.search;

public class RequestInfo {
	private String name;
	private String barcode;
	private float minprice;
	private float maxprice;
	private String unit;
	private int size;
	private int from;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public float getMinprice() {
		return minprice;
	}
	public void setMinprice(float minprice) {
		this.minprice = minprice;
	}
	public float getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(float maxprice) {
		this.maxprice = maxprice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public RequestInfo(String name, String barcode, float minprice, float maxprice, String unit, int size, int from) {
		this.name = name;
		this.barcode = barcode;
		this.minprice = minprice;
		this.maxprice = maxprice;
		this.unit = unit;
		this.size = size;
		this.from = from;
	}
	
	
}
