package com.java_jiji.moshi.proxy.staticproxy;

/**
 * Created by xc on 2019/8/22.
 */
public class TeacherDaoProxy implements ITeacherDao{
    private ITeacherDao teacherDao;
    
    public TeacherDaoProxy(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
    @Override
    
    public void teach() {
        System.out.println("开始");
        teacherDao.teach();
        System.out.println("结束");
    
    }
}
