package com.java_jiji.moshi.visitor;

public class Man extends Person {

	@Override
	public void accept(Action action) {
		
		action.getManResult(this);
	}

}
