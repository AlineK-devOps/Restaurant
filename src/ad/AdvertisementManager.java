package ad;

import rest.ConsoleHelper;

public class AdvertisementManager { //менеджер для оптимального подбора роликов для каждого заказа
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance(); //хранилище рекламных роликов
    private int timeSeconds; //время приготовления заказа в секундах

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){ //обработка рекламного видео
        ConsoleHelper.writeMessage("calling processVideos method");
    }
}
