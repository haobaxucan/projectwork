package com.ecpss.test.jvm;

/**
 * Created by xc on 2019/7/20.
 */
public class StringTest {
    
    /**
     * Code:
     0: ldc           #2                  // String aa  常量池中的常量值（int, float, string reference, object reference）入栈。
     2: astore_1
     3: ldc           #3                  // String bb  常量池中的常量值（int, float, string reference, object reference）入栈。
     5: astore_2
     6: new           #4                  // class java/lang/StringBuilder
     9: dup
     10: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
     13: aload_1
     14: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     17: aload_2
     18: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     21: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     24: astore_3
     25: new           #4                  // class java/lang/StringBuilder
     28: dup
     29: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
     32: aload_1
     33: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     36: aload_2
     37: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     40: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     43: areturn
     * @return
     */
    
    public String Test1(){
        
        String a="aa";
        String b="bb";
        String c=a+b;
        
        return  a+b;
    }
    
    public static void main(String[] args) {
        new StringTest().Test1();
    }
    
}
