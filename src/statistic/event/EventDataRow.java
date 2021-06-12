package statistic.event;

import java.util.Date;

public interface EventDataRow { //Интерфейс-маркер, является ли объект событием
    EventType getType(); //вернуть тип события
    Date getDate(); //вернуть дату создания записи
    int getTime(); //вернуть время или продолжительность
}
