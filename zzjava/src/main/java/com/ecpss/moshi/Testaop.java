package com.ecpss.moshi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Testaop {
	/**
	 * 代理模式
	 
	 代理模式的作用是：为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，
	 一个客户不想或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。
	 
	 代理模式一般涉及到的角色有：
	 
	 抽象角色：声明真实对象和代理对象的共同接口；
	 
	 代理角色：代理对象角色内部含有对真实对象的引用，从而可以操作真实对象，
	 同时代理对象提供与真实对象相同的接口以便在任何时刻都能代替真实对象。同时，代理对象可以在执行真实对象操作时
	 ，附加其他的操作，相当于对真实对象进行封装。
	 
	 真实角色：代理角色所代表的真实对象，是我们最终要引用的对象。(参见文献1)
	 * @return
	 */
	
	public static userService ProxyFactory(){
		
	final userService service=new UserServiceimpl();
//	userService.class.getInterfaces()
	 final Myaspect as=new Myaspect();

	userService proxjobj=(userService)Proxy.newProxyInstance(Testaop.class.getClassLoader(),
		new Class[]{userService.class}	, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				as.before();
				Object obj=method.invoke(service, args);
				return obj;
			}
		});
	
	return proxjobj;
	}		
}
