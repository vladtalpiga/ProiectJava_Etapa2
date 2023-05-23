package Project.Service;

import Project.People.Owner;

import java.util.Comparator;

public class myComparator implements Comparator <Owner> {
    public int compare (Owner o1, Owner o2) {
        return o1.getAge() - o2.getAge();
    }
}
