package Project.People;

import java.util.Scanner;

public abstract class Person {
    protected String name;
    protected int age;
    protected String homeCity; // city of residency

    public Person(String name, int age, String homeCity) {
        this.name = name;
        this.age = age;
        this.homeCity = homeCity;
    }

    public Person(){}
    public void read() {
        Scanner r = new Scanner(System.in);

        System.out.print("Name: ");
        this.name = r.nextLine();

        System.out.print("Age: ");
        this.age = Integer.parseInt(r.nextLine());

        System.out.print("Hometown: ");
        this.homeCity = r.nextLine();
    }

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

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    @Override
    abstract public String toString();
}
