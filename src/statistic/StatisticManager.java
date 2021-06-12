package statistic;

import statistic.event.EventDataRow;

public class StatisticManager { //регистрация событий в хранилище
    private static StatisticManager manager; //Singleton

    private StatisticManager() {
    }

    public static StatisticManager getInstance(){
        if (manager == null)
            manager = new StatisticManager();
        return manager;
    }

    public void register(EventDataRow data){ //регистрирует событие в хранилище

    }
}
