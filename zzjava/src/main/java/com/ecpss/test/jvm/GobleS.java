package com.ecpss.test.jvm;

/**
 * Created by xc on 2019/7/20.
 */
public class GobleS {
    /**
     *  Code:
     0: aload_0
     1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     4: aload_0
     5: ldc           #2                  // String aa
     7: putfield      #3                  // Field a:Ljava/lang/String;
     10: aload_0
     11: ldc           #4                  // String bb
     13: putfield      #5                  // Field b:Ljava/lang/String;
     16: return
     */
    /**
     *  Code:
     0: new           #6                  // class java/lang/StringBuilder
     3: dup
     4: invokespecial #7                  // Method java/lang/StringBuilder."<init>":()V
     7: aload_0
     8: getfield      #8                  // Field t:Ljava/lang/String;
     11: invokevirtual #9                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     14: aload_0
     15: getfield      #5                  // Field b:Ljava/lang/String;
     18: invokevirtual #9                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     21: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     24: astore_1
     25: aload_1
     26: areturn
     */
    
    String t; //从局部变量0中装载引用类型值入栈。
    String a="aa";  //常量池中的常量值（int, float, string )
    String b="bb";
    
    public String cc(){
        String c=t+b;
        return c;
    }
    public static void main(String[] args) {
    
    }
}
