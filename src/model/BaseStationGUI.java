package model;

import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;

/**
 * GUI representation of the SimpliSafe base station.
 * @author Lynda Tanielu
 */
public class BaseStationGUI extends JFrame {

    /**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 8787502425696528028L;
	
	/**
	 * The BaseStation represented by this GUI.
	 */
	private BaseStation station;
	
	/**
	 * The "LED" on the BaseStation, representing arm/alarm state.
	 */
    private JPanel LED;
    
    /**
     * Creates a new BaseStation GUI from the given BaseStation.
     * @param station The BaseStation to build the GUI from.
     */
    public BaseStationGUI(BaseStation station) {
        this.station = station;
        init();
    }
    
    /**
     * Initializes all GUI components associated with the BaseStation.
     */
    public void init() {
    	// set title and size
    	setTitle("Base Station");
        setSize(new Dimension(300, 450));
    	setMinimumSize(new Dimension(300, 450));
    	
    	// create GUI panel
    	JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.setBackground(Color.LIGHT_GRAY);
		
		// set up LED display
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

    /**
     * Updates LED display depending on whether the system is armed.
     */
    public void alarmed() {
        if (station.getAlarm().isActive()) {
            LED.setBackground(Color.RED);

            // flash LED is alarm is active
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
    
    /**
     * Refreshes the GUI display.
     */
    public void refresh() {
    	alarmed();
    }
}