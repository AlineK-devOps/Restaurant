package rest;

import kitchen.Cook;
import kitchen.Order;
import statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer { //кладёт новые заказы в очередь для поваров
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(); //очередь заказов

    public OrderManager() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        Thread.sleep(10);
                        if (!orderQueue.isEmpty()){
                            Set<Cook> cooks = StatisticManager.getInstance().getCooks();
                            for (Cook cook : cooks){
                                if (!cook.isBusy()){
                                    cook.startCookingOrder(orderQueue.take());
                                }
                            }
                        }
                    }
                }
                catch (InterruptedException ex){}
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        orderQueue.add((Order) arg); //добавляем заказ в очередь
    }
}
