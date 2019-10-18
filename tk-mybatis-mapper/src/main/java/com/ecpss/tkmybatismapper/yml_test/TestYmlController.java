package com.ecpss.tkmybatismapper.yml_test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.00
 * @date 2019/10/14
 */

@RestController
@Slf4j
public class TestYmlController {
    @Value("${server.port}")
    public int port;
    @RequestMapping("/yml")
    public String yml(){
        return String.valueOf(port);
    }

    @Autowired
    Person person;
    @Value("${person.first.addrs[0].addr}")
    private String addrname;

    @RequestMapping("/person")
    public String age(){
        System.out.println(person.getName()+"--"+person.getAge()+"--"+person.getAddrs());
        System.out.println(addrname+"解");
        return "";
    }

}
