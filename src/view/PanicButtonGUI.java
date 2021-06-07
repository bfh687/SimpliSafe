package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import main.SimpliSafe;
import model.BaseStation;
import model.panic.PanicButton;

/**
 * GUI representation of a panic button.
 * @author Blake Hamilton
 */
public class PanicButtonGUI extends JFrame {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -6521924404455857240L;
	
	/**
	 * The BaseStation associated with this panic button.
	 */
	private BaseStation station;
	
	/**
	 * The panic button represented by the GUI.
	 */
	private PanicButton panic;
	
	/**
	 * The ID number of the camera associated with the GUI.
	 */
	private int ID;
    
	/**
	 * Creates a GUI representation of a panic button in a SimpliSafe security system.
	 * @param station The BaseStation attached to the panic button.
	 * @param ID The ID of the panic button represented by the GUI.
	 */
    public PanicButtonGUI(BaseStation station, int ID) {
        this.station = station;
        this.panic = station.getPanicButtons().get(ID - 1);
        this.ID = ID;
        init();
    }
    
    /**
	 * Initializes all GUI components.
	 */
    private void init() {
    	setTitle("Panic Button" + ID);
        setSize(new Dimension(200, 200));
    	setMinimumSize(new Dimension(200, 200));
    	
    	JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);

		JButton panicButton = new JButton("Panic");
		panicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!station.getAlarm().isActive()) {
					panic.panic();
					SimpliSafe.refresh();
				}
			}
		});
		panel.add(panicButton);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}