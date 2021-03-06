package view;

import model.BaseStation;

import javax.swing.*;

import main.SimpliSafe;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * GUI representation of the system's tester.
 * @author Alibile Ugas
 */
public class TesterGUI extends JFrame {
	
    /**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 2260693441092707247L;
	
	/**
     * The alarms to be triggered
     */
    private JButton [] alarms = new JButton[8];
    
    /**
     * text area  of result
     */
    private JTextArea result;
    
    /**
     * The main panel of the GUI
     */
    private JPanel  myMainPanel = new JPanel();
    
    /**
     * The north panel that contains result
     */
    private JPanel  myNorthPanel = new JPanel();
    
    /**
     * The center panel the contains the buttons
     */
    private JPanel  myCenterPanel = new JPanel();
    
    /**
     * Home base to be used in tests
     */
    private BaseStation station;
    
    /**
	 * Creates a GUI representation of our SimpliSafe security system's tester.
	 * @param theBase The BaseStation attached to the tester.
	 * @throws FileNotFoundException
	 */
    public TesterGUI(BaseStation theBase) throws FileNotFoundException {
        this.station = theBase;
        this.setTitle("Trigger Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        result = new JTextArea(4, 10);
        preparePanels();
        this.add(myMainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        }
    
    /**
     * Refreshes all the system's GUI displays.
     */
    public void refresh() {
        SimpliSafe.refresh();
    }
    
    /**
     * Initializes all GUI components.
     */
    private void preparePanels() {
        myMainPanel.setLayout(new BorderLayout());
        myNorthPanel.setLayout(new BorderLayout());
        myCenterPanel.setLayout(new GridLayout(4, 2));
        myMainPanel.add(myNorthPanel, BorderLayout.NORTH);
        myMainPanel.add(myCenterPanel, BorderLayout.CENTER);
        myNorthPanel.add(result);
        result.setEditable(false);
        alarms[0] = new JButton("Carbon Monoxide Alarm");
        alarms[1] = new JButton("Entry Sensor Alarm");
        alarms[2] = new JButton("Glassbreak Alarm");
        alarms[3] = new JButton("Motion sensor Alarm");
        alarms[4] = new JButton("Smoke Alarm");
        alarms[5] = new JButton("Temperature Alarm");
        alarms[6] = new JButton("Water Alarm");
        alarms[7] = new JButton("Alarm Off");
        for (int i = 0; i < alarms.length; i++) {
            myCenterPanel.add(alarms[i]);
        }
        buttonListeners();
    }
    
    /**
     * Initializes all button's action listeners.
     */
    private void buttonListeners() {
	    alarms[0].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getCarbonMonoList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[1].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getEntrySensorList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[2].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getGlassSensorList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[3].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getMotionSensorList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[4].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getSmokeSensorList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[5].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getTemperatureSensorList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[6].addActionListener(e -> {
	    	if (!station.getAlarm().isActive()) {
		        result.setText(station.getWaterSensorList().get(0).detect());
		        refresh();
	    	}
	    });
	    alarms[7].addActionListener(e -> {
	        station.disarm(1234);
	        result.setText("Alarm cleared");
	        refresh();
	    });
    }
}