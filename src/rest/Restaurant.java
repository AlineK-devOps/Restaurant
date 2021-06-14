package rest;

import kitchen.Cook;
import kitchen.Waiter;

public class Restaurant { //ресторан
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args){
        Tablet tablet5 = new Tablet(5);
        Cook cook = new Cook("Amigo");
        Waiter waiter = new Waiter();

        tablet5.addObserver(cook);
        cook.addObserver(waiter);

        tablet5.createOrder();
        tablet5.createOrder();
        tablet5.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
