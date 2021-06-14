package rest;

import kitchen.Cook;
import kitchen.Order;
import kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant { //ресторан
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>(); //очередь заказов

    public static void main(String[] args){
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(ORDER_QUEUE);

        Cook cook2 = new Cook("Norman");
        cook2.setQueue(ORDER_QUEUE);

        List<Tablet> tablets = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Tablet tablet = new Tablet(i + 1);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        Thread threadCook1 = new Thread(cook1);
        threadCook1.start();

        Thread threadCook2 = new Thread(cook2);
        threadCook2.start();

        try {
            Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
