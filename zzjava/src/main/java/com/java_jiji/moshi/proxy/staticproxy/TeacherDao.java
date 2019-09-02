package com.java_jiji.moshi.proxy.staticproxy;

/**
 * Created by xc on 2019/8/22.
 */
public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("tttt");
    }
}
