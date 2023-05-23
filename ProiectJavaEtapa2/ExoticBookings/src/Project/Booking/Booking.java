package Project.Booking;

import Project.People.Client;

import java.util.Scanner;

public abstract class Booking {
    protected Client client;
    protected int duration; // duration of booking in days

    public Booking(Client client, int duration) {
        this.client = client;
        this.duration = duration;
    }
    public Booking (){}

    public void read () {
        Scanner r = new Scanner(System.in);

        System.out.print("Booking/rental duration (in days): ");
        int dur = Integer.parseInt(r.nextLine());

        while (dur < 1) {
            System.out.print("Reenter booking/rental duration: ");
            r = new Scanner(System.in);
            dur = Integer.parseInt(r.nextLine());
        }
        this.duration = dur;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    abstract public String toString();
}
