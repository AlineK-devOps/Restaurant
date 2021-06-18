package kitchen;

import rest.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer{ //повар
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object arg) { //заказ поступил
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(observable.toString(), name, ((Order)arg).getTotalCookingTime() * 60, ((Order)arg).getDishes())); //регистрируем событие
        setChanged();
        notifyObservers(arg); //сообщаем официантам, что заказ готов
    }

    @Override
    public String toString() {
        return name;
    }
}
