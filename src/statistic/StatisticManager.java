package statistic;

import kitchen.Cook;
import statistic.event.EventDataRow;
import statistic.event.EventType;

import java.util.*;

public class StatisticManager { //регистрация событий в хранилище

    private class StatisticStorage{ //хранилище
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType event : EventType.values()){
                storage.put(event, new ArrayList<>());
            }
        }

        private void put(EventDataRow data){ //поместить в хранилище
            storage.get(data.getType()).add(data);
        }
    }

    private StatisticManager.StatisticStorage statisticStorage = new StatisticStorage();
    private static StatisticManager manager; //Singleton
    private Set<Cook> cooks = new HashSet<>(); //список поваров

    private StatisticManager() {
    }

    public static StatisticManager getInstance(){
        if (manager == null)
            manager = new StatisticManager();
        return manager;
    }

    public void register(EventDataRow data){ //регистрирует событие в хранилище
        statisticStorage.put(data);
    } //регистрация записи в хранилище

    public void register(Cook cook){ //регистрация повара
        cooks.add(cook);
    }
}
