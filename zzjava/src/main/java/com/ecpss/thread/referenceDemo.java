package com.ecpss.thread;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by xc on 2019/8/7.
 */
public class referenceDemo {
    public static void main(String[] args) {
        PhantomReference<String> phantomReference = new PhantomReference<String>(new String("Misout的博客"), new ReferenceQueue<String>());
        System.out.println(phantomReference.get());

    }
}
