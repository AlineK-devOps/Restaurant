package kitchen;

import rest.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{ //повар
    private String name;
    private boolean busy; //занят ли повар
    private LinkedBlockingQueue<Order> queue; //очередь заказов

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order){
        busy = true;
        ConsoleHelper.writeMessage(name + " Start cooking - " + order);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes())); //регистрируем событие
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(order); //сообщаем официантам, что заказ готов
        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while (true){
                Thread.sleep(10);
                if (!queue.isEmpty() && !this.isBusy()){
                    this.startCookingOrder(queue.take());
                }
            }
        }
        catch (InterruptedException ex){}
    }
}
