package com.ecpss.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分支情况测试
 * git checkout -b xxx  创建本地切换分支
 * git push origin xxx  分支推送到远程
 * git merge xxx dev  合并开发分支到 xxx(本地分支xxx改变)
 * git push origin xxx  (远程分支xxx改变)
 */
@SpringBootApplication
public class TkmybatisApplication {
//可怕
    public static void main(String[] args) {
        SpringApplication.run(TkmybatisApplication.class, args);
    }

}
