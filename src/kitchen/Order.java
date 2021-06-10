package kitchen;

import rest.Tablet;

import java.util.List;

public class Order { //заказ
    private final Tablet tablet; //планшет
    protected List<Dish> dishes; //список блюд в заказе

    public Order(Tablet tablet) {
        this.tablet = tablet;
    }
}
