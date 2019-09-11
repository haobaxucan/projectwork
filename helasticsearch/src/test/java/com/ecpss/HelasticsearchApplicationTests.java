package com.ecpss;

import com.ecpss.book.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelasticsearchApplicationTests {
	@Autowired
	BookRepository repository;

	@Test
	public void add() {
		
		Book book=new Book();
		book.setName("xc");
		repository.save(book);
		
	}
	
	@Test
	public void query() {
		List<Book> ao = repository.findByNameLike("ao");
		System.out.println(ao.size());
		
	}
	@Test
	public void queryById() {
		Book book = repository.findBooKById(1);
		System.out.println(book);
		
	}
	@Test
	public void del() {
		repository.deleteById(1);
		
		
	}
	@Test
	public void save() {
		repository.save(new Book("3","chaoshen22","xc"));
		
	}
	
	@Test
	public void natvie() {//Type（类型-表）
		
		Book book = repository.findByName("chaoshen22");
		
		System.out.println(book.getName());
//		System.out.println(chaoshen22.size());
		
		
	}
}
