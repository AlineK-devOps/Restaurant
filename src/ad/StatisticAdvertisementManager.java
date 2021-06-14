package ad;

import java.util.*;

public class StatisticAdvertisementManager { //сбор статистики из хранилища рекламных роликов
    private static StatisticAdvertisementManager manager;
    private AdvertisementStorage storage = AdvertisementStorage.getInstance(); //хранилище рекламных роликов

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance(){
        if (manager == null)
            manager = new StatisticAdvertisementManager();
        return manager;
    }

    public TreeMap<String, Integer> getActiveVideos(){ //получить список активных рекламных роликов
        TreeMap<String, Integer> activeVideos = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });

        for (Advertisement ad : storage.list()){
            if (ad.isActive()) {
                activeVideos.put(ad.getName(), ad.getHits());
            }
        }
        return activeVideos;
    }

    public TreeSet<String> getNonActiveVideos(){ //получить список неактивных рекламных роликов
        TreeSet<String> nonActiveVideos = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });

        for (Advertisement ad : storage.list()){
            if (!ad.isActive()) {
                nonActiveVideos.add(ad.getName());
            }
        }
        return nonActiveVideos;
    }
}
