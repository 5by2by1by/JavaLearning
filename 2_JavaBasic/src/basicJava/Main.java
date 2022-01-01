package basicJava;      // Java的package类似于C++的namespace
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Main {

    /**
     * 功能输出！ （快速生成方法注释：在方法上输入/** + 回车。）
     * @param index
     * @param obj
     */
    public static void print(int index, Object obj){
        System.out.println(String.format("{%d}, %s", index, obj.toString()));
    }

    public static void demoString(){
        String s = "hello";
        for(char c : s.toCharArray()){
            print(1, c);
        }
        // String 不可变，每次修改都会创建一个新的String，效率较低；如果要修改的话，建议使用StringBuilder 和 StringBuffer
        String str = "hello, dblinux";
        print(2, str.charAt(4));
        print(3, str.codePointAt(6));
        print(4, str.contains("hello"));
        print(5, str.endsWith("x"));
        print(6, str.concat("!!"));
        print(7, str.startsWith("h"));
        print(8, str.lastIndexOf('o'));
        print(9, str.toUpperCase());
        print(10, str.toLowerCase());
        print(11, str.replace('b','d'));
        print(12, str.replace("db", "bd"));
        print(13, str.replaceAll("o|l", "d"));  // 正则表达式，将str里面所有的 o和l 字母变成 d
        print(14, str.replaceAll("hello", "hi"));


        StringBuilder sb = new StringBuilder();
        sb.append(true);
        sb.append(3.3);
        print(15, sb.toString());

    }


    // 开发过程中，数据结构用的最多的就是 ArrayList、HashMap、HashTable、Set...

    /**
     * ArrayList 动态数组
     * LinkedList 链表
     */
    public static void demoList(){
        List<String > strList1 = new ArrayList<String>();
        for(int i = 0; i < 4; ++i){
            strList1.add(String.valueOf(i));
        }
        print(1, strList1);

        List<String > strList2 = new ArrayList<String>();
        for(int i = 0; i < 4; ++i){
            strList2.add(String.valueOf(i + 5));
        }
        strList1.addAll(strList2);
        print(2, strList1);
        strList1.remove(String.valueOf(6));
        print(3, strList1);
        print(4, strList1.get(4));
        Collections.reverse(strList1);
        print(5, strList1);
        Collections.sort(strList1, new Comparator<String>() {   // 匿名函数
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        print(6, strList1);
    }

    /**
     * set 集合，三大数据结构，集合、列表、键值对
     * 作用：去重！ 底层使用红黑树实现
     *
     */
    public static void demoSet(){
        // 前面是set，后面是HashSet？ 因为后面可以随便改，大家同时继承于Set接口。面向对象的基础：重视接口，而非实现。
        Set<String> strSet = new HashSet<String>();
        for(int i = 0; i < 3; ++i){
            strSet.add(String.valueOf(i));
        }
        print(1, strSet);

        strSet.remove(2);
        print(2, strSet.contains(1));
        strSet.addAll(Arrays.asList(new String[] {"A", "B", "C"}));
        print(3, strSet);

        for(String val : strSet){
            print(4, val);
        }
    }

    /**
     * hashMap: 快速查找 O(1)
     */
    public static void demoKeyValue(){
        Map<String, String> m = new HashMap<String, String>();
        for(int i = 0; i < 4; ++i){
            m.put(String.valueOf(i), String.valueOf(i * i));
        }
        print(1, m);

        for(Map.Entry<String, String> item : m.entrySet()){     // 一个键值对就是一个entry
            print(2, item);
        }

        print(3, m.keySet());
        print(3, m.values());
        print(4, m.containsKey("3"));
        print(5, m.containsValue("9"));
        print(6, m.get("2"));
        m.replace("1", "A");
        print(7, m);
    }

    /**
     * 异常
     */
    public static void demoException(){
        try {
            // do something
            print(1, "hello");
            int b = 2;
            b /= 0;     // 除数不可以为零，直接被Exception捕获
            String a = null;
            a.indexOf('1');     // 无法对空指针进行索引，所以会被npe捕获异常
        }catch (NullPointerException npe){  // 捕获空指针异常
            print(3, "npe");
        }catch (Exception e){
            // logger.... 一般是打日志
            print(4, "Ex");
        }finally {  // 无论是否有异常，都会执行的代码块
            print(2, "finally");
        }
    }

    /**
     * 一些通用类，比如随机类
     */
    public static void demoCommon(){
        // salt uuid;
        Random r = new Random();
        r.setSeed(2); // 如果设定了种子数，那么伪随机数是不会改变的。
        for(int i = 0 ; i < 5; ++i){
            print(1, r.nextInt(100));
        }

        List<Integer> arr = Arrays.asList(new Integer[] {1,2,3,4,5});
        print(2, arr);
        Collections.shuffle(arr);
        print(3, arr);

        // Date
        Date date = new Date();
        print(4, date);
        print(5, date.getTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        print(6, df.format(date));

        print(7, UUID.randomUUID());
    }

    public static void demoClass(){
        Animal animal = new Animal("jim", 1);
        animal.say();
        animal = new human("Li", 13, "CN");
        animal.say();
    }

    public static void main(String[] args) {
        // demoString();
        // demoSet();
        // demoKeyValue();
        // demoException();
        // demoCommon();
        demoClass();
    }
}
