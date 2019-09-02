package com.ecpss.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by xc on 2019/9/2.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "xc",type = "book")
public class Book {
    @Id
    private String id;
    private String name;
    private String auth;
}
