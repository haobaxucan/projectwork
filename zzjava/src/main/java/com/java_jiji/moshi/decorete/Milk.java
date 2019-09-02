package com.java_jiji.moshi.decorete;

public class Milk extends Decorator {
	
	public Milk(Drink obj) {
		super(obj);
		setDes(" 牛奶 ");
		setPrice(2.0f);
	}

}
