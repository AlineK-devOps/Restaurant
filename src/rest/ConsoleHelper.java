package rest;

import kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper { //вспомогательный класс для работы с консолью
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){ //вывод message в консоль
        System.out.println(message);
    }

    public static String readString() throws IOException { //чтение строки с консоли
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException { //просит пользователя выбрать блюдо и добавляет его в список
        List<Dish> order = new ArrayList<>();

        System.out.println("Список блюд:");
        System.out.println(Dish.allDishesToString());
        while (true){
            System.out.print("Введите название блюда: ");
            String dish = ConsoleHelper.readString();

            if (dish.equals("exit"))
                break;
            try{
                order.add(Dish.valueOf(dish));
            }
            catch (Exception exception){
                System.out.println("Данного блюда нет в меню");
            }
        }
        return order;
    }
}
