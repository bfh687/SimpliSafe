package model;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class BaseStationGUI extends JFrame {

    /**
	 * GUI serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private BaseStation station;

	/**
    * GUI representation of a LED, for alarm
    */
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
    }

}