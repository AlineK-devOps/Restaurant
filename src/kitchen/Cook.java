package kitchen;

import rest.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable{ //повар
    private String name;
    private boolean busy; //занят ли повар

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
}
