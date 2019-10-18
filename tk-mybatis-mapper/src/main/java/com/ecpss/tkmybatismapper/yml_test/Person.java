package com.ecpss.tkmybatismapper.yml_test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.00
 * @date 2019/10/14
 */
@Data
@Component
@ConfigurationProperties(prefix = "person.first")
public class Person {

    private String name;
    private int age;
    private List<Addr> addrs;


}

