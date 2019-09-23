package com.ecpss;

import com.ecpss.domain.User;
import com.ecpss.es.Goods;
import com.ecpss.mappper.GoodsMapper;
import com.ecpss.mappper.RedisMapper;
import com.ecpss.service.RedisUserService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HgoodApplicationTests {
    @Autowired
    DataSource dataSource;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    RedisUserService redisUserService;
    
    @Test
    public void redis() {
        User user = new User().setName("xc").setAge(12).setAddress("湖南");
//		RedisKeyUtil.getKeyWithColumn()
    
    }
    
    @Autowired
    private RedisMapper redisMapper;
    
    @Test
    public void contextLoads() throws Exception {
        User user = redisUserService.getByid(-1L);
//        User user = redisUserService.getHot("cold");
        System.out.println(user);//User(id=2, name=cold, address=xc, age=13)
    }
    
    @Test
    public void testRedission() throws Exception {
    
    }
    
    @Autowired
    JestClient jestClient;
    
    @Test
    public void testJestEsAdd() throws Exception {
        Goods goods = new Goods().setId(1).setName("好看的").setDesc("这个神木").setPrice(11.3);
        Index index = new Index.Builder(goods).index("goods").type("good").build();
        jestClient.execute(index);
    }
    @Test
    public void testJestEsQuery() throws Exception {
        String json="{\n" +
                "  \"query\":{\n" +
                "    \"match\": {\"name\":\"red\"}\n" +
                "  }\n" +
                "}";
    
        Search build = new Search.Builder(json).addIndex("movie_index").addType("movie").build();
        SearchResult result = jestClient.execute(build);
        List<SearchResult.Hit<Goods, Void>> hits = result.getHits(Goods.class);
        for (SearchResult.Hit<Goods,Void> hit:hits){
            Goods source = hit.source;
        }
    
    }
    @Autowired
    private GoodsMapper goodsMapper;
    @Test
    public void queryGoods() throws Exception {
        List<Goods> goods = goodsMapper.queryAll();
        System.out.println(goods);
    }
    
    @Test
    public void contextLoad1() throws Exception {
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String orginalFilename = "D:\\a\\33.jpg";
        String[] upload_file = storageClient.upload_file(orginalFilename, "jpg", null);
        
        String url = "192.168.88.130/";
        for (int i = 0; i < upload_file.length; i++) {
            String s = upload_file[i];
            System.out.println("s = " + s);
            url += s;
            
        }
        System.out.println(url);
        
    }
    
    
    
}
