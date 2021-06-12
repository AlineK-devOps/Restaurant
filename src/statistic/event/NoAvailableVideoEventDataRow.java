package statistic.event;

import java.util.Date;

public class NoAvailableVideoEventDataRow implements EventDataRow {//нет ни одного видео-ролика, который можно показать во время приготовления заказа
    private Date currentDate = new Date(); //текущая дата
    private  int totalDuration; //время приготовления заказа в секундах

    public NoAvailableVideoEventDataRow(int totalDuration){
        this.totalDuration = totalDuration;
    }

    @Override
    public EventType getType() { //вернуть тип события
        return EventType.NO_AVAILABLE_VIDEO;
    }
}
