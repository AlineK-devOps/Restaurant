package rest;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{ //генерирует заказы
    private List<Tablet> tabletList; //список всех планшетов
    private int interval; //интервал создания заказа

    public RandomOrderGeneratorTask(List<Tablet> tabletList, int ORDER_CREATING_INTERVAL) {
        this.tabletList = tabletList;
        this.interval = ORDER_CREATING_INTERVAL;
    }

    @Override
    public void run() { //создание заказов для случайного планшета
        try{
            while (true){
                int k = (int)(Math.random() * tabletList.size());
                Tablet tablet = tabletList.get(k);
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        }
        catch (InterruptedException ex){}
    }
}
