

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessio
 */
public class Model {

    private List<ModelObserver> observers;
    private int seconds;
    private int minute;
    private int hours;

    public Model() {
        observers = new ArrayList<>();
        this.seconds = 0;
        this.minute = 0;
        this.hours = 0;
    }

    public void updateTime() throws InterruptedException {

            Thread.sleep(1000);
            if (seconds < 59) {
                seconds = seconds + 1;
            } else {
                seconds = 0;
                if (minute < 59) {
                    minute = minute + 1;
                } else {
                    minute = 0;
                    if (hours < 23) {
                        hours = hours + 1;
                    } else {
                        hours = 0;
                        minute = 0;
                        seconds = 0;
                    }
                }
            }
            for (ModelObserver ob : observers) {
                ob.modelChanged(hours, minute, seconds);
            }
    }

    public void addObserver(ModelObserver ob) {
        observers.add(ob);
    }

    public void removeObserver(ModelObserver ob) {
        observers.remove(ob);
    }
}
