package com.ecpss;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xc on 2019/8/6.
 */
public class Test1 {
    String name;
    
    public Test1(String name) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        "1".hashCode();
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    public static void main(String[] args) {
        Map<Test1, String> map = new HashMap<>(4);
        int hello = new Test1("hello").hashCode();
        int hello2 = new Test1("hello").hashCode();
        /**
         *    为什么会生成不同的哈希码值呢？上面我们在比较s1和s2的时候不是生成了同样的哈希码吗？原因就在于我们自己写的Student类并没有重新自己的hashcode()和equals()方法，所以在比较时，是继承的object类中的hashcode()方法，而object类中的hashcode()方法是一个本地方法，比较的是对象的地址（引用地址），使用new方法创建对象，两次生成的当然是不同的对象了，造成的结果就是两个对象的hashcode()返回的值不一样，所以Hashset会把它们当作不同的对象对待。
         ---------------------
         版权声明：本文为CSDN博主「flyingsen」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
         原文链接：https://blog.csdn.net/zj15527620802/article/details/88547914
         */
        System.out.println(hello+"--"+hello2);
    
//        map.put(new Test1("hello"), "hello");
//        String hello = map.get(new Test1("hello"));
//        System.out.println(hello);
    }
}
