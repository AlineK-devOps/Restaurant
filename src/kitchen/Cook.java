package kitchen;

import rest.ConsoleHelper;

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
        setChanged();
        notifyObservers(arg); //сообщаем официантам, что заказ готов
    }

    @Override
    public String toString() {
        return name;
    }
}
