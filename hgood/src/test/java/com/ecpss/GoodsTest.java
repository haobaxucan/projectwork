package com.ecpss;

import com.ecpss.es.Goods;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by xc on 2019/9/7.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsTest {
    @Autowired
    private JestClient client;
    
    @Test
    public void goodsAddd() throws Exception {
//        查詢數據庫
//        重新封裝 es bean
//        放置到 es db中
//     ********主要就是查询了

//        new builder
        String json = "{\n" +
                "  \"query\":{\n" +
                "    \"match\": {\"name\":\"red\"}\n" +
                "  }\n" +
                "}";
        System.out.println("-----------------------------------");
        
        Search build = new Search.Builder(json).addIndex("movie_index").addType("movie").build();
        SearchResult result = client.execute(build);
        List<SearchResult.Hit<Goods, Void>> hits = result.getHits(Goods.class);
        for (SearchResult.Hit<Goods, Void> hit : hits) {
            Goods source = hit.source;
        }
        
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter();
        boolQueryBuilder.must();
        sourceBuilder.query(boolQueryBuilder);
    }
    

    
}
