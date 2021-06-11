package rest;

import ad.AdvertisementManager;
import ad.NoVideoAvailableException;
import kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable { //планшет
    private final int number; //номер планшета
    public static Logger logger = Logger.getLogger(Tablet.class.getName()); //причина исключения

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){ //создаём заказ из тех блюд, что выберет пользователь
        Order order = null;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString()); //вывод заказа
            if (!order.isEmpty()){
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
                setChanged(); // добавлен новый заказ
                notifyObservers(order); //отправляем изменения наблюдателям
            }
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Console is unavailable."); //исключение ввода-вывода
        } catch (NoVideoAvailableException ex){
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
