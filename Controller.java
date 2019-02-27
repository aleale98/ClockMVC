
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements ViewObserver {

    private Model model;

    public Controller(Model model, View view) {
        this.model = model;
        model.addObserver(view);
        view.addObserver(this);

    }

    @Override
    public void viewActionPerformed() {
        try {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        model.updateTime();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(task, 0, 1000);
            //Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
