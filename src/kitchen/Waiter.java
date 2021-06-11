package kitchen;

import rest.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer { //официант
    @Override
    public void update(Observable o, Object arg) { //заказ приготовлен
        ConsoleHelper.writeMessage(arg + " was cooked by " + o);
    }
}
