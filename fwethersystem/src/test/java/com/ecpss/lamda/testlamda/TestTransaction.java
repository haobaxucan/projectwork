package com.ecpss.lamda.testlamda;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTransaction {
	
	List<Transaction> trs = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		trs = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 1950)
		);
	}
	
	//1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
	@Test
	public void test1(){
		
		List<Integer> list = Arrays.asList(1, 3, 5, 6);
//		list.stream().map(x->x+x).collect(Collectors.toList()).stream().forEach(System.out::println);
		Integer integer = list.stream().reduce(Integer::sum).get();
//		System.out.println(integer);
		trs.stream().filter((x)->x.getYear()==2011).map(Transaction::getValue).sorted(Integer::compare).forEach(System.out::println);
		trs.stream().filter((x)->x.getYear()==2011).sorted((x,y)->Integer.compare(x.getValue(),y.getValue())).forEach(System.out::println);
		
	}
	
	//2. 交易员都在哪些不同的城市工作过？
	@Test
	public void test2(){
		trs.stream().map((x)->x.getTrader().getCity()).distinct().forEach(System.out::println);
		
	}
	
	//3. 查找所有来自剑桥的交易员，并按姓名排序
	@Test
	public void test3(){
		trs.stream().filter((x)->x.getTrader().getCity().equals("Cambridge")).map(Transaction::getTrader).distinct().
				sorted((x,y)->x.getName().compareTo(y.getName())).forEach(System.out::println);
		
		
	
	}
	
	//4. 返回所有交易员的姓名字符串，按字母顺序排序
	@Test
	public void test4(){
		trs.stream().map((t)->t.getTrader().getName()).sorted().distinct().forEach(System.out::println);
	
	}
	

	
	//5. 有没有交易员是在米兰工作的？
	@Test
	public void test5(){
		int milan = trs.stream().filter((x) -> x.getTrader().getCity().equals("Milan")).collect(Collectors.toList()).size();
		System.out.println(milan);
	}
	
	
	//6. 打印生活在剑桥的交易员的所有交易额
	@Test
	public void test6(){
		
		Integer integer = trs.stream().filter(x -> x.getTrader().getCity().equals("Cambridge")).
				map(Transaction::getValue).reduce((x, y) -> x + y).get();
		System.out.println(integer);
		
	}
	
	
	//7. 所有交易中，最高的交易额是多少
	@Test
	public void test7(){
		Integer integer = trs.stream().map(Transaction::getValue).collect(Collectors.maxBy(Integer::compare)).get();
		System.out.println(integer);
	}
	
	//8. 找到交易额最小的交易
	@Test
	public void test8(){
		Transaction transaction = trs.stream().collect(Collectors.minBy((x, y) -> (Integer.compare(x.getValue(), y.getValue())))).get();
		System.out.println(transaction);
	}

}