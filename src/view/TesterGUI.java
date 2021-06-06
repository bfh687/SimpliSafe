package view;

import model.BaseStation;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class TesterGUI extends JFrame {
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

    public TesterGUI(BaseStation theBase) throws FileNotFoundException {
        this.station = theBase;
        this.setTitle("Trigger Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        result = new JTextArea(4, 10);
        preparePanels();
        this.add(myMainPanel);
        this.pack();
        }

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

    private void buttonListeners() {
	    alarms[0].addActionListener(e -> {
	        result.setText(station.getCarbonMonoList().get(0).detect());
	        refresh();
	    });
	    alarms[1].addActionListener(e -> {
	        result.setText(station.getEntrySensorList().get(0).detect());
	        refresh();
	    });
	    alarms[2].addActionListener(e -> {
	        result.setText(station.getGlassSensorList().get(0).detect());
	        refresh();
	    });
	    alarms[3].addActionListener(e -> {
	        result.setText(station.getMotionSensorList().get(0).detect());
	        refresh();
	    });
	    alarms[4].addActionListener(e -> {
	        result.setText(station.getSmokeSensorList().get(0).detect());
	        refresh();
	    });
	    alarms[5].addActionListener(e -> {
	        result.setText(station.getTemperatureSensorList().get(0).detect());
	        refresh();
	    });
	    alarms[6].addActionListener(e -> {
	        result.setText(station.getWaterSensorList().get(0).detect());
	        refresh();
	    });
	    alarms[7].addActionListener(e -> {
	        station.disarm(1234);
	        result.setText("Alarm cleared");
	        refresh();
	    });
    }
    
    public void refresh() {
        SimpliSafe.refresh();
    }

    public static void main(String[] args) throws FileNotFoundException {
        BaseStation h = new BaseStation();
        new TesterGUI(h);
    }
}
