package model;

import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;

public class BaseStationGUI extends JFrame {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private BaseStation station;
    private JPanel LED;

    public BaseStationGUI(BaseStation station) {
        this.station = station;
        init();
    }

    public void init() {
        setSize(new Dimension(300, 450));
    	setMinimumSize(new Dimension(300, 450));

    	JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.setBackground(Color.LIGHT_GRAY);

        LED = new JPanel();
        LED.setBackground(Color.BLUE);
        LED.setPreferredSize(new Dimension(40, 40));
        panel.add(LED, BorderLayout.NORTH);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //if alarm is on or not
    public void alarmed() {
        if (station.getAlarm().isActive()) {
            LED.setBackground(Color.RED);
            // Sample loop to flash every 1 seconds
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                private int state;
                public void run() {
                    if (state % 2 == 0) {
                        LED.setVisible(true);
                    } else {
                        LED.setVisible(false);
                    }
                    if (!station.getAlarm().isActive()) {
                    	timer.cancel();
                    	timer.purge();
                    	LED.setVisible(true);
                    }
                    state++;
                }
            };
            timer.schedule(task, 0, 500);
        } else {
            LED.setBackground(Color.BLUE);
            LED.setVisible(true);
        }
    }
    
    public void refresh() {
    	alarmed();
    }
}