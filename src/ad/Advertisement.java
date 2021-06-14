package ad;

public class Advertisement { //рекламное объявление
    private Object content; //видео
    private String name; //название ролика
    private long initialAmount; //стоимость рекламы в копейках
    private int hits; //количество оплаченных показов
    private int duration; //продолжительность в секундах
    private long amountPerOneDisplaying; //стоимость одного показа в копейках

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;

        if (hits != 0)
            amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits(){
        return hits;
    }

    public void revalidate(){ //уменьшаем количество показов
        if (hits <= 0)
            throw new UnsupportedOperationException();
        hits--;
    }

    public boolean isActive(){ //не закончились ли рекламные показы
        return hits > 0;
    }
}
