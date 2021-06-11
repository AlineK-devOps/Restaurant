package ad;

public class AdvertisementManager { //менеджер для оптимального подбора роликов для каждого заказа
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance(); //хранилище рекламных роликов
    private int timeSeconds; //время приготовления заказа в секундах

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){ //обработка рекламного видео
        /*подобрать список видео, просмотр который обеспечивает максимальную выгоду */

        if (storage.list().isEmpty()){ //если нет рекламных видео
            throw new NoVideoAvailableException();
        }

        /*отобразить все рекламные ролики, отобранные для показа*/
    }
}
