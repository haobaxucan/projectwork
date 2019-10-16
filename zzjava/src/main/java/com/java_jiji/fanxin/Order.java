package com.java_jiji.fanxin;

import lombok.Data;
import lombok.Setter;

import javax.ws.rs.GET;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order<T> {

    private T t;
    @Setter
    public List<T> list=new ArrayList<>();

    public static void main(String[] args) {
        Order<Boolean> order=new Order<>();


    }

}
