package kitchen;

import rest.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order{
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() { //генерация случайного заказа
        dishes = new ArrayList<>();
        Dish[] allDishes = Dish.values();

        int countOfDishes = (int) (Math.random() * 3 + 2); //количество блюд в заказе

        for (int i = 0; i <= countOfDishes; i++){
            int randomDish = (int) (Math.random() * allDishes.length); //рандомное блюдо от 0 до 4
            dishes.add(allDishes[randomDish]);
        }
    }
}
