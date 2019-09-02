package com.java_jiji.moshi.strategy.improve;

public class NoFlyBehavior implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println(" 不会飞  ");
	}

}
