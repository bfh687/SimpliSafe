package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import model.BaseStation;

/**
 * GUI representation of a camera.
 * @author Blake Hamilton
 */
public class CameraGUI extends JFrame {

    /**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 7001434813806010254L;
	
	/**
	 * The BaseStation associated with this camera.
	 */
	private BaseStation station;
	
	/**
	 * The ID number of the camera associated with the GUI.
	 */
    private int ID;
    
    /**
     * The image representing the camera's stream.
     */
    private JLabel imageDisplay;
    
    /**
	 * Creates a GUI representation of a camera in a SimpliSafe security system.
	 * @param station The BaseStation attached to the camera.
	 */
    public CameraGUI(BaseStation station, int ID) {
        this.station = station;
        this.ID = ID;
        init();
    }
    
    /**
	 * Refreshes the GUI display.
	 */
    public void refresh() {
    	BufferedImage image = station.getCameras().get(ID - 1).stream();
		imageDisplay.setIcon(new ImageIcon(image));
    }
    
    /**
	 * Initializes all GUI components.
	 */
    private void init() {
    	setTitle("Camera" + ID);
        setSize(new Dimension(480, 400));
    	setMinimumSize(new Dimension(480, 400));

    	JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		
		BufferedImage image = station.getCameras().get(ID - 1).stream();
		imageDisplay = new JLabel(new ImageIcon(image));
		panel.add(imageDisplay);
		
		JPanel togglePanel = new JPanel();
		JButton toggle = new JButton("Toggle Camera");
		toggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				station.getCameras().get(ID - 1).toggle();
				refresh();
				panel.repaint();
			}
		});
		togglePanel.add(toggle);
		panel.add(togglePanel, BorderLayout.NORTH);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}