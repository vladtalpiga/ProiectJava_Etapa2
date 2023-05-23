package Bin;

public class Service {

    private static Service instance = null;
    Service(){}

    public static synchronized Service getInstance() {
        if (instance == null)
            instance = new Service();
        return instance;
    }
}
