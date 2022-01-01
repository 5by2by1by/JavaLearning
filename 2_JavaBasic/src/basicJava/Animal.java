package basicJava;

public class Animal implements talk{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 给变量添加set和get方法
     * 选中，右键单击，generate getter and setter。
     */
    private String name;
    private int age;
    public Animal(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void say(){
        System.out.println("i am a animal!");
    }
}
