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

    @Override
    public String toString() { //отображение заказа
        if (dishes.isEmpty()) return "";

        StringBuilder order = new StringBuilder();
        for (Dish dish : dishes)
            order.append(dish).append(",").append(" ");

        return String.format("Your order: [%s] of %s", order.substring(0, order.length() - 2), tablet);
    }
}
