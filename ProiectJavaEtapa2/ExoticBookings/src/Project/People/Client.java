package Project.People;

import java.util.Scanner;

public class Client extends Person {
    int balance; // bank balance
//    private List<Car> myGarage = new ArrayList<Car>();
    public Client (){}

    public Client(String name, int age, String homeCity, int balance) {
        super(name, age, homeCity);
        this.balance = balance;
    }

    public Client(int balance) {
        this.balance = balance;
    }

    public void read() {
        Scanner r = new Scanner(System.in);

        System.out.print("Name: ");
        this.name = r.nextLine();

        System.out.print("Age: ");
        this.age = Integer.parseInt(r.nextLine());

        System.out.print("Hometown: ");
        this.homeCity = r.nextLine();

        System.out.print("Balance: ");
        this.balance =  Integer.parseInt(r.nextLine());
    }
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + ", from " + homeCity + ", age: " + age + ", balance: " + balance;
    }
}
