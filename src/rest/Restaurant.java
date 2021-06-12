package rest;

import ad.Advertisement;
import kitchen.Cook;
import kitchen.Waiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Restaurant { //ресторан
    public static void main(String[] args){
        Tablet tablet5 = new Tablet(5);
        Cook cook = new Cook("Amigo");
        Waiter waiter = new Waiter();

        tablet5.addObserver(cook);
        cook.addObserver(waiter);

        tablet5.createOrder();
    }

}
