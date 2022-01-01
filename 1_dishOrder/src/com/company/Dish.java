package com.company;

/*
* 菜品类 Dish
* 提供有参数的构造方法
* */
public class Dish {
    int id;
    String name;
    double price;

    // 快捷键生成构造方法  Alt + Insert
    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
