package com.crazykid.springboot.study.lambdastudy;

import java.util.UUID;
import java.util.function.*;

/**
 * Lambda语法学习(函数式接口)
 * @author meijinyu
 * @date 2019/12/15 20:46
 */
public class LambdaStudy {
    public static void main(String[] args) {
        // 接受一个泛型，返回布尔值结果。
        Predicate<String> pre = (String username) -> "admin".equals(username);
        System.out.println(pre.test("admin"));
        System.out.println(pre.test("manager"));

        // 接受一个泛型，没有返回值。
        Consumer<String> con = (String message) -> System.out.println("发送成功，信息:" + message);
        Consumer<String> con2 = (String message) -> System.out.println("发送成功2，信息:" + message);

        // con运行后接着运行con2
        Consumer<String> con3 = con.andThen(con2);
        con3.accept("111");

        // 函数式接口。<T, R> 接受一个T对象，返回一个R对象
        // 模拟校验性别，male返回1，否则返回0。
        Function<String, Integer> fun = (String gender) -> "male".equals(gender)? 1 : 0 ;
        System.out.println(fun.apply("male"));
        System.out.println(fun.apply("female"));


        // 工厂类接口 不接受任何参数，直接通过get()获取指定类型对象
        Supplier<String> sup = () -> UUID.randomUUID().toString();
        System.out.println(sup.get());
        System.out.println(sup.get());
        System.out.println(sup.get());

        // 接受参数T, 执行业务处理后, 返回更新后的T
        UnaryOperator<Integer> uo = (Integer i) -> i * 2;
        System.out.println(uo.apply(2));


        // 接受两个T, 执行业务处理后, 返回一个T
        BinaryOperator<Integer> bo = (Integer x, Integer y) -> x * y;
        System.out.println(bo.apply(3, 4));
    }
}
