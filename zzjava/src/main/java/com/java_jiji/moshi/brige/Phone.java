package com.java_jiji.moshi.brige;

public abstract class Phone {
	
	private Brand brand;
	
	
	public Phone(Brand brand) {
		this.brand = brand;
	}
	protected void open() {
		this.brand.open();
	}
	protected void close() {
		brand.close();
	}
	protected void call() {
		brand.call();
	}
	
}
