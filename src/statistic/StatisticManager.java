package statistic;

import statistic.event.EventDataRow;
import statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager { //регистрация событий в хранилище

    private class StatisticStorage{ //хранилище
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType event : EventType.values()){
                storage.put(event, new ArrayList<>());
            }
        }
    }

    private StatisticManager.StatisticStorage statisticStorage = new StatisticStorage();
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
