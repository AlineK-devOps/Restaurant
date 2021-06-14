package kitchen;

import rest.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable{ //повар
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order){
        ConsoleHelper.writeMessage("Start cooking - " + order);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes())); //регистрируем событие
        setChanged();
        notifyObservers(order); //сообщаем официантам, что заказ готов
    }

    @Override
    public String toString() {
        return name;
    }
}
