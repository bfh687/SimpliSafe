package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import javax.swing.*;

import model.BaseStation;

public class CameraGUI extends JFrame {

    private BaseStation station;
    private JLabel imageDisplay;
    
    public CameraGUI(BaseStation station) {
        this.station = station;
        init();
    }

    public void init() {
        setSize(new Dimension(480, 400));
    	setMinimumSize(new Dimension(480, 400));

    	JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		
		BufferedImage image = station.getCameras().get(0).stream();
		imageDisplay = new JLabel(new ImageIcon(image));
		panel.add(imageDisplay);
		
		JPanel togglePanel = new JPanel();
		JButton toggle = new JButton("Toggle Camera");
		toggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				station.getCameras().get(0).toggle();
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
    
    public void refresh() {
    	BufferedImage image = station.getCameras().get(0).stream();
		imageDisplay.setIcon(new ImageIcon(image));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	new CameraGUI(new BaseStation());
    }
}