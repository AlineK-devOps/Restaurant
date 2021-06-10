package rest;

import kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet { //планшет
    private final int number; //номер планшета
    public static Logger logger = Logger.getLogger(Tablet.class.getName()); //причина исключения

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder(){ //создаём заказ из тех блюд, что выберет пользователь
        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString()); //вывод заказа
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Console is unavailable."); //исключение ввода-вывода
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
