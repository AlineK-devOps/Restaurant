package kitchen;

public enum Dish { //Блюдо
    FISH,
    STEAK,
    SOUP,
    JUICE,
    WATER;

    public static String allDishesToString(){ //отобразить список всех блюд в ресторане
        StringBuilder result = new StringBuilder();
        for (Dish dish : Dish.values())
            result.append(dish).append(" ");
        return result.toString().trim();
    }
}
