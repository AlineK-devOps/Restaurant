package rest;

import kitchen.Cook;

import java.io.IOException;

public class Restaurant { //ресторан
    public static void main(String[] args){
        Tablet tablet5 = new Tablet(5);
        Cook cook = new Cook("Amigo");

        tablet5.addObserver(cook);

        tablet5.createOrder();
        tablet5.createOrder();
        tablet5.createOrder();
        tablet5.createOrder();
    }
}
