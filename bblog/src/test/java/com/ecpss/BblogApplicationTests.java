package com.ecpss;

import com.ecpss.domain.test.ArticeRes;
import com.ecpss.domain.test.Article;
import com.ecpss.domain.test.Book;
import com.ecpss.domain.test.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BblogApplicationTests {
    /**
     * ctrl +f12 查看类里面的方法
     */
    
    /**
     * 测试jest clinet
      */
    @Autowired
    private JestClient jestClient;
    @Autowired
    private ArticeRes articeRes;
    @Autowired
    BookRepository bookRepository;
    
    /**
     * 给es 中索引一个文档
     */
    @Test
    public void contextLoads() {
        Article article=new Article();
        article.setId(1);
        article.setTitle("xc");
        article.setAuthor("chaoshen");
//      构建一个索引
        Index index = new Index.Builder(article).index("aa").type("news").build();
                                                   //index 索引 类型//一些新闻
     
        try {
            //   执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    测搜索
    @Test
    public void search(){
        String json="";
        Search search = new Search.Builder(json).addIndex("aa").addType("news").build();
    
        try {
            //   执行
            SearchResult execute = jestClient.execute(search);
            System.out.println(execute.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
//    @Before
    public void init(){
        articeRes.index(new Article(1,"张","xc"));
        articeRes.index(new Article(2,"许","妖精的"));
        articeRes.index(new Article(3,"吴超","xc"));
        articeRes.index(new Article(4,"蝴蝶","微博宠爱冲好的哇偶就"));
    }
    
    @Test
    public void testSave(){
        bookRepository.index(new Book());
    
    }
   
}
