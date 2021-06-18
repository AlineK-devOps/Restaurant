package statistic.event;

import kitchen.Dish;

import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow{ //повар приготовил заказ
    private Date currentDate = new Date(); //текущая дата
    private String tabletName; //номер планшета
    private String cookName; //имя повара
    private int cookingTimeSeconds; //время приготовления заказа в секундах
    private List<Dish> cookingDishes; //список блюд для приготовления

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishes){
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishes = cookingDishes;
    }

    @Override
    public EventType getType() { //вернуть тип события
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate() {
        return null;
    }

    public String getCookName(){
        return cookName;
    }

    @Override
    public int getTime() {
        return 0;
    }
}