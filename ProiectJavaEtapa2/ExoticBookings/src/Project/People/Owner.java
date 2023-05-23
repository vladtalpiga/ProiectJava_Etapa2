package Project.People;

import java.util.Scanner;

public class Owner extends Person {
    private int income;
    private int nrProp; // number of properties owned

    public Owner(String name, int age, String homeCity) {
        super(name, age, homeCity);
        this.income = 0;
        this.nrProp = 0;
    }

    public Owner(int income) {
        this.income = 0;
        this.nrProp = 0;
    }

    public Owner(){}

    public void read() {
        Scanner r = new Scanner(System.in);

        System.out.print("Name: ");
        this.name = r.nextLine();

        System.out.print("Age: ");
        this.age = Integer.parseInt(r.nextLine());
    }

    public int getIncome() {
        return income;
    }
    public void setIncome(int income) {
        this.income = income;
    }

    public int getNrProp() {
        return nrProp;
    }
    public void setNrProp(int nrProp) {
        this.nrProp = nrProp;
    }

    public void incrementNrProp (Owner owner) {
        owner.setNrProp(owner.getNrProp() + 1);
    }

    public void addIncome (Owner owner, int value) {
        owner.setIncome(owner.getIncome() + value);
    }

    @Override
    public String toString() {
        return name + ", aged " + age + ", has an income of: " + income + ", owning " + nrProp + " properties";
        // homeCity not used
    }
}
