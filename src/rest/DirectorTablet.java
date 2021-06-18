package rest;

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

    }

    public void printActiveVideoSet(){ //список активных роликов и оставшееся количество показов по каждому

    }

    public void printArchivedVideoSet(){ //список неактивных роликов

    }
}
