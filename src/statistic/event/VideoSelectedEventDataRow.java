package statistic.event;

import ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow{ //выбрали набор видео-роликов для заказа
    private Date currentDate = new Date(); //текущая дата
    private List<Advertisement> optimalVideoSet; //список видео-роликов, отобранных для показа
    private long amount; //сумма денег в копейках
    private int totalDuration; //общая продолжительност показаотобранный рекламных роликов

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration){
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
    }
}
