package rest;

import kitchen.Cook;
import kitchen.Waiter;
import statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant { //ресторан
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args){
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Norman");

        StatisticManager.getInstance().register(cook1);
        StatisticManager.getInstance().register(cook2);

        List<Tablet> tablets = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            tablets.add(new Tablet(i + 1));
            tablets.get(i).addObserver(cook1);
            tablets.get(i).addObserver(cook2);
        }

        try {
            Thread tread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
            tread.start();
            Thread.sleep(1000);
            tread.interrupt();
        } catch (InterruptedException e) {
        }

        /*Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);*/

        /*DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();*/
    }
}
