package com.ecpss;

import com.ecpss.controller.dto.test.People;
import com.ecpss.controller.dto.test.Person;
import com.ecpss.utils.JpaPageHelper;
import com.ecpss.utils.PageInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Ignore
public class DdiancanApplicationTests {
	private Logger logger= LoggerFactory.getLogger(DdiancanApplicationTests.class);
	
	/**
	 * 日志的使用
	 */
	@Test
	public void contextLoads() throws Exception{
		
		List<Person> list=new LinkedList();
		Person p1=new Person("xc","城市",21);
		Person p2=new Person("de","城市",21);
		Person p3=new Person("fd","城市21",21);
		Person p4=new Person("bf","21城市",21);
		Person p5=new Person("vhfdf","患难",21);
		Person p6=new Person("vx","廉租",21);
		Person p7=new Person("vjgz","调教",21);
		Person p8=new Person("vduf","城市从点",21);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
//		Pageable pageable =new PageRequest(4,4);
		Pageable pageable =new PageRequest(1,4);
		PageImpl<Person> page = new PageImpl<Person>(list, pageable, list.size());
		System.out.println(page.getTotalPages());
		System.out.println(page.hasNext());
		System.out.println(page.hasContent());
		System.out.println(page.hasPrevious());
		System.out.println(page.getNumberOfElements());
		System.out.println(page.getContent());
		
		
		
	}
	@Test
	public void a(){
		
		People people=new People();
//		people.setAge(12);
		people.setName("cc");
//		people.setAddr("ss");
		Person person=new Person();
		
		BeanUtils.copyProperties(people,person);
		System.out.println(person);
		
//		ProductInfo productInfo=new ProductInfo();
//		productInfo.setStore(1);
//		productInfo.setCreateTime(new Date());
//		ProductInfo productInfo1=new ProductInfo();
//		BeanUtils.copyProperties(productInfo,productInfo1);
//		System.out.println(productInfo.getStore());
//		List<String> cc=new ArrayList<>();
//		List<String> dd=new ArrayList<>();
//		cc.add("cc");
//		cc.add("aa");
//		cc.add("a");
//		List<String> collect = cc.stream().collect(Collectors.toList());
////		cc.stream().f
//		System.out.println(collect.size());
//		BeanUtils.copyProperties(cc,dd);
//		System.out.println(dd.size());
	}

}
