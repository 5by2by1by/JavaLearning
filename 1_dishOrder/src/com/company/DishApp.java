package com.company;

// 点菜的主程序

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DishApp {
    // 提前准备菜品，展示给用户，同时用户可以点菜

    static List<Dish> dishList = new ArrayList<>();
    static List<Dish> personDishList = new ArrayList<>();

    public static void main(String[] args) {
        // 初始化菜品
        initDish();
        // 创建扫描器对象，与外界交互
        Scanner sc = new Scanner(System.in);

        while(true){
            //给用户展示主菜单
            showMenu();
            int num = sc.nextInt();
            switch (num){
                case 1:
                    // 点菜一次性就点明白了，所以写成死循环，不然直接退出到外面的死循环中。
                    while(true){
                        showDishMenu();
                        int id = sc.nextInt();
                        if(id == 0)
                            break;
                        Dish dish = dishList.get(id - 1);
                        System.out.println("亲，您点了：" + dish.name);
                        // 用户点菜之后，将菜添加到已点菜单中。
                        personDishList.add(dish);
                    }
                case 2:
                    showPersonDish();
                    break;
                case 3:
                    buy();
                    return;
            }
        }
    }

    public static void showDishMenu() {
        // 展示菜品，直接遍历dishList即可。
        System.out.println("---please order---");
        // 遍历集合 快捷键 dishList.fori
        // length 是数组的属性，size() 是集合的方法，length() 是String的方法
        for (int i = 0; i < dishList.size(); i++) {
            Dish dish = dishList.get(i);
            System.out.println(dish.id + "\t" + dish.name + "\t" + dish.price);
        }
        System.out.println("输入序号下单，按0返回主菜单");
    }

    public static void showPersonDish() {
        System.out.println("---你已经点了以下菜品---");
        // 增强 for 循环，personDishList.for 即可
        for (Dish dish : personDishList) {
            System.out.println(dish.id + "\t" + dish.name + "\t" + dish.price);
        }
    }

    public static void buy() {
        System.out.println("---请稍等，正在结算---");
        // 把已点菜品的 price 属性相加即可
        double res = 0.0;
        for (Dish dish : personDishList) {
            res += dish.price;
        }
        System.out.println("您一共消费" + res);
    }

    public static void showMenu() {
        System.out.println("---主菜单---");
        System.out.println("菜单\t\t\t 1");
        System.out.println("已点菜品\t\t 2");
        System.out.println("买单\t\t\t 3");
        System.out.println("---根据编号开始点菜---");
    }

    public static void initDish(){
        Dish dish = new Dish(1, "香辣肉丝", 29.0);
        dishList.add(dish);
        Dish dish2 = new Dish(2, "炖甲鱼", 89.0);
        dishList.add(dish2);
        Dish dish3 = new Dish(3, "香辣花生米", 19.0);
        dishList.add(dish3);
        Dish dish4 = new Dish(4, "地三鲜", 39.0);
        dishList.add(dish4);
        Dish dish5 = new Dish(5, "土豆烧鸡", 59.0);
        dishList.add(dish5);
    }
}