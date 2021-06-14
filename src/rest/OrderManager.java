package rest;

import kitchen.Order;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer { //кладёт новые заказы в очередь для поваров
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(); //очередь заказов

    @Override
    public void update(Observable o, Object arg) {
        orderQueue.add((Order) arg); //добавляем заказ в очередь
    }
}
