package basicJava;

public class human extends Animal{
    private String country;
    public human(String name, int age, String country){
        super(name, age);
        this.country = country;
    }

    public void say(){
        System.out.println("This is  " + this.getName() + "  from  " + country);
    }
}
