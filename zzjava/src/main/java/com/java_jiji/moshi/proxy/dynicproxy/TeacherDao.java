package com.java_jiji.moshi.proxy.dynicproxy;

import com.java_jiji.moshi.proxy.staticproxy.ITeacherDao;

/**
 * Created by xc on 2019/8/22.
 */
public class TeacherDao implements com.java_jiji.moshi.proxy.dynicproxy.ITeacherDao {
    @Override
    public void teach() {
        System.out.println("tttt");
    }
}
