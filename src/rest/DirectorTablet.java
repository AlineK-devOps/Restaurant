package rest;

import ad.StatisticAdvertisementManager;
import statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet { //планшет директора для просмотра статистики
    public void printAdvertisementProfit(){ //какую сумму заработали на рекламе, группировка по дням
        TreeMap<Date, Float> profitForDay = StatisticManager.getInstance().profitForDay();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-y", Locale.ENGLISH);

        float total = 0;
        for (Map.Entry<Date, Float> entry : profitForDay.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", dateFormat.format(entry.getKey()), entry.getValue()));
            total += entry.getValue();
        }

        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total));
    }

    public void printCookWorkloading(){ //загрузка повара, группировка по дням
        TreeMap<Date, TreeMap<String, Integer>> durationOfWork = StatisticManager.getInstance().getDurationOfWork();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-y", Locale.ENGLISH);

        for (Map.Entry<Date, TreeMap<String, Integer>> entry : durationOfWork.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s", dateFormat.format(entry.getKey()))); //вывод даты

            for (Map.Entry<String, Integer> cook : entry.getValue().entrySet()){ //вывод поваров
                ConsoleHelper.writeMessage(String.format("%s - %d min", cook.getKey(), (int)Math.ceil(1.0 * cook.getValue() / 60)));
            }
        }
    }

    public void printActiveVideoSet(){ //список активных роликов и оставшееся количество показов по каждому
        TreeMap<String, Integer> activeVideos = StatisticAdvertisementManager.getInstance().getActiveVideos();

        for (Map.Entry<String, Integer> entry : activeVideos.entrySet()){
            ConsoleHelper.writeMessage(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }
    }

    public void printArchivedVideoSet(){ //список неактивных роликов
        TreeSet<String> nonActiveVideos = StatisticAdvertisementManager.getInstance().getNonActiveVideos();

        for (String name : nonActiveVideos)
            ConsoleHelper.writeMessage(name);
    }
}
