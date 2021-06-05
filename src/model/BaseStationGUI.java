package model;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import model.BaseStation;

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
        if (station.isArmed()) {
            LED.setBackground(Color.RED);
        }
        else
            LED.setBackground(Color.BLUE);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	BaseStation s = new BaseStation();
    	s.arm();
    	BaseStationGUI gui = new BaseStationGUI(s);
    	gui.alarmed();
    }


}