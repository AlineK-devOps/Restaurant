package kitchen;

public enum Dish { //Блюдо
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private int duration; //время приготовления блюда в минутах

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString(){ //отобразить список всех блюд в ресторане
        StringBuilder result = new StringBuilder();
        for (Dish dish : Dish.values())
            result.append(dish).append(" ");
        return result.toString().trim();
    }
}
