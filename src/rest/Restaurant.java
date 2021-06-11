package rest;

import kitchen.Cook;
import kitchen.Waiter;

public class Restaurant { //ресторан
    public static void main(String[] args){
        Tablet tablet5 = new Tablet(5);
        Cook cook = new Cook("Amigo");
        Waiter waiter = new Waiter();

        tablet5.addObserver(cook);
        cook.addObserver(waiter);

        tablet5.createOrder();
        tablet5.createOrder();
        tablet5.createOrder();
        tablet5.createOrder();
    }
}
