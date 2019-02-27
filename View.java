
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Alessio
 */
public class View extends JFrame implements ActionListener, ModelObserver {

    private JButton add;
    private JLabel label;
    private List<ViewObserver> observers;

    public View() {
        JPanel panel = new JPanel();
        this.setTitle("Digital Clock");
        add = new JButton("start");
        panel.add(add);
        label = new JLabel();
        label.setFont(new Font("Courier New", Font.BOLD, 26));
        label.setSize(200, 200);
        label.setText("0:0:0");
        this.add(panel, BorderLayout.SOUTH);
        this.add(label, BorderLayout.NORTH);
        add.addActionListener(this);
        observers = new ArrayList<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setSize(200, 200);
        this.setLocation(dimension.width / 2 - getWidth() / 2, dimension.height / 2 - getHeight() / 2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("start")) {
            for (ViewObserver ob : observers) {
                System.out.println("viewactionperformed");
                ob.viewActionPerformed();
            }
            add.setEnabled(false);
        }
    }

    public void addObserver(ViewObserver ob) {
        observers.add(ob);
    }

    public void removeObserver(ViewObserver ob) {
        observers.remove(ob);
    }

    @Override
    public void modelChanged(int hours, int minute, int seconds) {
        label.setText(hours + ":" + minute + ":" + seconds);
    }
}
