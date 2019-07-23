package com.ecpss.lamda;

import com.google.common.base.Function;
import io.lettuce.core.output.KeyValueStreamingChannel;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xc on 2019/7/14.
 */
public class PublicTest {
    /**
     * 函数中只有一个抽象方法的接口就是函数式接口
     */
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55),
            new Employee(105, "值田七", 38, 15555.55)
    );
    @Test
    public void T1(){
        List<Integer> list = Arrays.asList(1, 3,55,2,5562,77,2);
        List<String> strings = Arrays.asList("aa","",null,"ddc");
//        list.stream().filter((x)->x>2).forEach(System.out::print);
//        list.stream().distinct().sorted().filter((x)->x>76).forEach(System.out::print);
        strings.stream().filter(s->s!=null&&!s.equals("")).forEach(System.out::print);
//        strings.stream().
    
        emps.stream().sorted((x,y)->{
            if(x.getAge()==y.getAge()){
                return -x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(),y.getAge());
            }
        }).forEach(System.out::print);
    }
    @Test
    public void T2(){
        List<String> strings = Arrays.asList("aa","ddc");
        strings.stream().map(s->s.toUpperCase()).forEach(System.out::print);
        List<Integer> as = Arrays.asList(1,2,3,44);
        Integer integer = as.stream().reduce((x, y) -> x + y).get();
        System.out.println(integer);
    
    }
    /**
     * 方法引用((注意lamada 体已经有方法实现了，可以使用方法引用
     * () 参数列表
     */
    @Test
    public void T3(){
        Consumer<T> c = System.out::print;
        Employee employee=new Employee();
        Supplier<String> stringSupplier=employee::getName;
        List<Integer> collect = emps.stream().map(Employee::getAge).collect(Collectors.toList());
        Comparator<Integer> compare = Integer::compare;//静态方法引用
        emps.stream().map((e)->e.getAge()).sorted(Integer::compare);
        
        //若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式：
        Function<Employee, Integer> age = Employee::getAge;
        
    
        /**
         * 构造器使用
         *
         * 构造器的参数列表，需要与函数式接口中参数列表保持一致！
         */
        Supplier<Employee> e = Employee::new;
        Employee employee1 = e.get();//无参
        
        Function<String,Employee> f=Employee::new;//根据参数匹配
        
    
    }
    @Test
    public void T4(){
        Employee employee = emps.stream().findFirst().orElse(null);
        System.out.println(employee);
        List<String> strings = Arrays.asList("aa","ddc");
//        strings.stream().flatMap()
//        strings.stream().map();
        List<String> strings1 = Arrays.asList("mm", "dd", "de");
        String collect = strings1.stream().collect(Collectors.joining(","));
        System.out.println(collect);
        String s = "aa,vv,de";
        String[] split = s.split(",");
        Stream<String> stream = Arrays.stream(split);//数组创建流
        String collect1 = stream.collect(Collectors.joining("-"));//aa-vv-de
        //
        Long collect2 = emps.stream().collect(Collectors.counting());
        System.out.println(collect2);//总数
        Double av = emps.stream().collect(
                Collectors.averagingDouble(
//                        Employee::getSalary==两步  （无参）第一步 第一个参数是调用，第二返回值
                        (e)->e.getSalary()));//平均值
    
        System.out.println(av);
        Runnable aa = this::aa;
    //最高工资
        Employee employee1 = emps.stream().collect(Collectors.maxBy((e, e1) -> Double.compare(e.getSalary(), e1.getSalary()))).get();
        System.out.println(employee);
        
    
    }
        @Test
        public void aVoid5(){//分组
            Map<Integer, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getAge));
            //分区partitioningBy Map<Boolean, List<T>> 根据true或false进行分区
            Map<Boolean, List<Employee>> collect1 = emps.stream().collect(Collectors.partitioningBy((e) -> e.getAge() > 21));
            System.out.println(collect1);
            
    
    
        }
    
    
    
    
    
    
    public void aa(){
    
    }

}
