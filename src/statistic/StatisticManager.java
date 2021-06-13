package statistic;

import kitchen.Cook;
import statistic.event.CookedOrderEventDataRow;
import statistic.event.EventDataRow;
import statistic.event.EventType;
import statistic.event.VideoSelectedEventDataRow;

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
        } //добавить событие в хранилище

        public Map<EventType, List<EventDataRow>> getStorage(){
            return storage;
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

    public TreeMap<Date, Float> profitForDay(){ //считает общую прибыль от рекламы за каждый день
        if (!statisticStorage.getStorage().containsKey(EventType.SELECTED_VIDEOS)) //если реклама не показывалась
            return null;

        TreeMap<Date, Float> profit = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });

        for (EventDataRow data : statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS)){
            Date dateEvent = data.getDate(); //дата события
            Calendar calendar = new GregorianCalendar(dateEvent.getYear() + 1900, dateEvent.getMonth(), dateEvent.getDate());
            if (profit.containsKey(calendar.getTime())){
                float currentAmount = profit.get(calendar.getTime());
                currentAmount += (float)((VideoSelectedEventDataRow)data).getAmount() / 100;
                profit.replace(calendar.getTime(), currentAmount);
            }
            else profit.put(calendar.getTime(), (float)((VideoSelectedEventDataRow)data).getAmount() / 100);
        }
        return profit;
    }

    public TreeMap<Date, TreeMap<String, Integer>> getDurationOfWork(){ //считает продолжительность работы каждого повара за каждый день
        if (!statisticStorage.getStorage().containsKey(EventType.COOKED_ORDER)) //если повара не готовили
            return null;

        TreeMap<Date, TreeMap<String, Integer>> durationOfWork = new TreeMap<>(new Comparator<Date>() { //даты - повара, которые работали в этот день
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });

        for (EventDataRow data : statisticStorage.getStorage().get(EventType.COOKED_ORDER)){
            Date dateEvent = data.getDate(); //дата события
            Calendar calendar = new GregorianCalendar(dateEvent.getYear() + 1900, dateEvent.getMonth(), dateEvent.getDate());
            String cookName = ((CookedOrderEventDataRow)data).getCookName(); //имя повара
            int cookingTime = data.getTime(); //время работы над заказом
            if (durationOfWork.containsKey(calendar.getTime())){
                TreeMap<String, Integer> workDay = durationOfWork.get(calendar.getTime()); //получаем рабочую смену

                if (workDay.containsKey(cookName)){
                    cookingTime += workDay.get(cookName);
                    workDay.replace(cookName, cookingTime);
                }
                else workDay.put(cookName, cookingTime);
            }
            else{
                durationOfWork.put(calendar.getTime(), new TreeMap<>(new Comparator<String>() { //внутренние мапы, имя повара - время работы
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                }));
                durationOfWork.get(calendar.getTime()).put(cookName, cookingTime);
            }
        }
        return durationOfWork;
    }
}
