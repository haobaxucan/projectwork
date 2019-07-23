package com.ecpss.test.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xc on 2019/7/19.
 */
@Data
@NoArgsConstructor
public class User {
    private String name;
    private String age;
    
    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
    
    public User aa(){
        String age = this.age;
        bb();
        User user = new User();
        
        return this;
    }
    public void ff(){
        aa();
    }
    
    public static void bb(){
    
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        
        User user = (User) o;
        
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return age != null ? age.equals(user.age) : user.age == null;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
    
    public static void main(String[] args) {
        User user = new User("a","21");
        user.aa();
        boolean s = "xca".equals("xcc");
        System.out.println(s);
    
        char[] chars = "xc".toCharArray();
        String str = String.valueOf(chars);
        System.out.println(str);
    
    }
}
