package com;

import com.ecpss.spring.domain.instance.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xc on 2019/8/24.
 */
public class SaoCaoZuo {
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        list1.add("as");
        list1.add("as1");
        list1.add("as2");
        list1.add("");
        list1.stream().filter((s) -> s != null && !s.equals("")).forEach(System.out::print);
        Object user = null;


//        判断对象为空的写法
        if (user == null) {//user.null

        }

//        判断对象不为空
        if (user != null) {//user.notnull

        }
        if (user != null) {//user.nn

        }
//    条件判断
        if (args.length > 0) { //0.if

        }

//     增量 for 循环
        List list = new ArrayList();
        for (Object o : list) {//list.for

        }
//        list.stream().forEach();
//普通for循环
        for (int i = 0; i < list.size(); i++) {//list.fori

        }
        System.out.println("aa");//"aa".sout


    }


}
