package com.ecpss.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xc on 2019/7/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String xx;
    public void aa(){
        System.out.println("ca");
    }
}
