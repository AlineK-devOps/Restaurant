package ad;

public class AdvertisementStorage { //хранилище рекламных роликов
    private static AdvertisementStorage advertisementStorage;

    private AdvertisementStorage() {
    }

    public static AdvertisementStorage getInstance(){
        if (advertisementStorage == null)
            advertisementStorage = new AdvertisementStorage();

        return advertisementStorage;
    }
}
