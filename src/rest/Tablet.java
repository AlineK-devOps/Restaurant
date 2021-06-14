package rest;

import ad.AdvertisementManager;
import ad.NoVideoAvailableException;
import kitchen.Order;
import kitchen.TestOrder;

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

    public void createOrder(){ //создаём заказ из тех блюд, что выберет пользователь
        Order order = null;
        try {
            order = new Order(this);
            sendInfoAboutOrder(order);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Console is unavailable."); //исключение ввода-вывода
        } catch (NoVideoAvailableException ex){
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    public void createTestOrder(){ //тестовый заказ, рандомный выбор блюд
        Order order = null;
        try {
            order = new TestOrder(this);
            sendInfoAboutOrder(order);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Console is unavailable."); //исключение ввода-вывода
        } catch (NoVideoAvailableException ex){
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    private void sendInfoAboutOrder(Order order) { //сообщаем о создании заказа
        ConsoleHelper.writeMessage(order.toString()); //вывод заказа
        if (!order.isEmpty()) {
            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            setChanged(); // добавлен новый заказ
            notifyObservers(order); //отправляем изменения наблюдателям
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
