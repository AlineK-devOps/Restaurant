package ad;

import rest.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager { //менеджер для оптимального подбора роликов для каждого заказа
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance(); //хранилище рекламных роликов
    private int timeSeconds; //время приготовления заказа в секундах

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){ //обработка рекламного видео
        if (storage.list().isEmpty()){ //если нет рекламных видео
            throw new NoVideoAvailableException();
        }

        /*подобрать список видео, просмотр который обеспечивает максимальную выгоду*/
        List<Advertisement> ads = new ArrayList<>(); //набор рекламных роликов для заказа
        for (Advertisement ad : storage.list()){
            if (ad.getDuration() <= timeSeconds && ad.getHits() > 0)
                ads.add(ad);
        }
        List<Advertisement> bestSet = makeAdsSet(ads); //наилучший набор рекламных роликов для исходного заказа

        /*отобразить все рекламные ролики, отобранные для показа*/
        //в порядке уменьшения стоимости показа одного ролика в копейках
        Comparator<Advertisement> firstSort = (o1, o2) -> Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
        //в порядке увеличения стоимости показа одной секунды в тысячных частях копейки
        Comparator<Advertisement> secondSort = Comparator.comparingLong(o -> 1000 * o.getAmountPerOneDisplaying() / o.getDuration());

        bestSet.sort(firstSort.thenComparing(secondSort)); //сортировка набора

        for (Advertisement ad : bestSet){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(), ad.getAmountPerOneDisplaying(), 1000 * ad.getAmountPerOneDisplaying() / ad.getDuration()));
            ad.revalidate();
        }
    }

    private List<Advertisement> makeAdsSet(List<Advertisement> ads){ //создание наилучшего набора рекламы
        List<List<Advertisement>> allSets = new ArrayList<>(); //все перестановки
        getSets(0, ads, allSets);

        List<Advertisement> bestSet = new ArrayList<>(); //лучший набор рекламы для заказа

        for (List<Advertisement> list : allSets){
            List<Advertisement> tempSet = getBestSet(list);
            if (getAmountAds(tempSet) > getAmountAds(bestSet))
                bestSet = new ArrayList<>(tempSet);
            else if (getAmountAds(tempSet) == getAmountAds(bestSet)){
                if (getTimeAds(tempSet) > getTimeAds(bestSet))
                    bestSet = new ArrayList<>(tempSet);
                else if (getTimeAds(tempSet) == getTimeAds(bestSet)){
                    if (tempSet.size() < bestSet.size())
                        bestSet = new ArrayList<>(tempSet);
                }
            }
        }
        return bestSet;
    }

    private List<Advertisement> getBestSet(List<Advertisement> set){ //выбор наилучшего набора в перестановке
        List<Advertisement> bestSet = new ArrayList<>();

        List<Advertisement> temp = new ArrayList<>();
        for (Advertisement ad : set){
            temp.add(ad);
            if (getTimeAds(temp) <= timeSeconds){
                if (getAmountAds(temp) > getAmountAds(bestSet))
                    bestSet.add(ad);
                else if (getAmountAds(temp) == getAmountAds(bestSet)){
                    if (getTimeAds(temp) > getTimeAds(bestSet))
                        bestSet.add(ad);
                    else if (getTimeAds(temp) == getTimeAds(bestSet)){
                        if (temp.size() < bestSet.size())
                            bestSet.add(ad);
                    }
                }
            }
            else break;
        }
        return bestSet;
    }

    private static void getSets(int t, List<Advertisement> ads, List<List<Advertisement>> allSets){ //получение всех перестановок
        if (t == ads.size() - 1){ //Вывод очередной перестановки
            allSets.add(new ArrayList<>(ads));
        }
        else{
            for (int j = t; j < ads.size(); j++){ //Запускаем процесс обмена
                Advertisement temp = ads.get(t);
                ads.set(t, ads.get(j));
                ads.set(j, temp);
                t++;

                getSets(t, ads, allSets); //Рекурсивный вызов

                t--;
                temp = ads.get(t);
                ads.set(t, ads.get(j));
                ads.set(j, temp);
            }
        }
    }

    private int getAmountAds(List<Advertisement> ads){ //сумма денег, полученная от показов
        int amount = 0;
        for (Advertisement ad : ads)
            amount += ad.getAmountPerOneDisplaying();
        return amount;
    }

    private int getTimeAds(List<Advertisement> ads){ //общее время показа рекламы
        int timeAds = 0;
        for (Advertisement ad : ads)
            timeAds += ad.getDuration();
        return timeAds;
    }
}
