package ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage { //хранилище рекламных роликов
    private static AdvertisementStorage advertisementStorage;
    private final List<Advertisement> videos = new ArrayList<>(); //список доступных рекламных роликов

    private AdvertisementStorage() {
        videos.add(new Advertisement(new Object(), "First Video", 5000, 100, 3 * 60));
        videos.add(new Advertisement(new Object(), "Second Video", 100, 10, 15 * 60));
        videos.add(new Advertisement(new Object(), "Third Video", 400, 2, 10 * 60));
    }

    public static AdvertisementStorage getInstance(){
        if (advertisementStorage == null)
            advertisementStorage = new AdvertisementStorage();

        return advertisementStorage;
    }

    public List<Advertisement> list(){ //получение списка доступных роликов
        return videos;
    }

    public void add(Advertisement advertisement){ //добавление нового ролика в список
        videos.add(advertisement);
    }
}
