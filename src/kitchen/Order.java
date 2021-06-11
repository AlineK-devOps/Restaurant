package kitchen;

import rest.ConsoleHelper;
import rest.Tablet;

import java.io.IOException;
import java.util.List;

public class Order { //заказ
    private final Tablet tablet; //планшет
    protected List<Dish> dishes; //список блюд в заказе

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder(); //инициализация списка выбранных блюд
    }

    public int getTotalCookingTime(){ //вычисление времени приготовления заказа
        int time = 0;

        for (Dish dish : dishes)
            time += dish.getDuration();

        return time;
    }

    public boolean isEmpty(){ //есть ли в заказе блюда
        return dishes.isEmpty();
    }

    @Override
    public String toString() { //отображение заказа
        if (dishes.isEmpty()) return "";

        StringBuilder order = new StringBuilder();
        for (Dish dish : dishes)
            order.append(dish).append(",").append(" ");

        return String.format("Your order: [%s] of %s, cooking time %dmin", order.substring(0, order.length() - 2), tablet, getTotalCookingTime());
    }
}
