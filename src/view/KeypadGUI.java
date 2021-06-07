package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.SimpliSafe;
import model.BaseStation;

/**
 * GUI representation of a keypad.
 * @author Blake Hamilton
 */
public class KeypadGUI extends JFrame {

	/**
	 * Generated GUI serial version UID.
	 */
	private static final long serialVersionUID = -861921561207918092L;
	
	/**
	 * The BaseStation associated with this keypad.
	 */
	private BaseStation station;
	
	/**
	 * The textfield where user input is held.
	 */
	private JTextField textField;
	
	/**
	 * GUI representation of a LED.
	 */
	private JPanel LED;
	
	/**
	 * The ID number of the keypad associated with the GUI.
	 */
	private int ID;
	
	/**
	 * Creates a GUI representation of a keypad in a SimpliSafe security system.
	 * @param station The BaseStation attached to the keypad.
	 */
	public KeypadGUI(BaseStation station, int ID) {
		this.station = station;
		this.ID = ID;
		init();
	}
	
	/**
	 * Refreshes the GUI display.
	 */
	public void refresh() {
		if (station.isArmed() || station.getAlarm().isActive()) {
			LED.setBackground(Color.RED);
			flashLED();
		} else {
			LED.setBackground(Color.GREEN);
		}
	}
	
	/**
	 * Initializes all GUI components.
	 */
	private void init() {
		setTitle("Keypad" + ID);
		setSize(new Dimension(300, 450));
		setMinimumSize(new Dimension(300, 450));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		
		JPanel textFieldPanel = createTextField();
		textFieldPanel.setBorder(new EmptyBorder(20, 20, 10, 20));
		panel.add(textFieldPanel, BorderLayout.NORTH);
		
		JPanel keypadPanel = createKeypad();
		keypadPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
		
		panel.add(keypadPanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates the textfield and LED display components.
	 * @return Panel holding the textfield and LED display components.
	 */
	private JPanel createTextField() {
		JPanel panel = new JPanel(new BorderLayout());
		textField = new JTextField(10);
		textField.setPreferredSize(new Dimension(0, 30));
		textField.setEditable(false);
		panel.add(textField);

		LED = new JPanel();
		LED.setBackground(Color.GREEN);
		LED.setPreferredSize(new Dimension(30, 30));
		panel.add(LED, BorderLayout.EAST);
		
		return panel;
	}
	
	/**
	 * Creates the keypad component.
	 * @return Panel holding the keypad component.
	 */
	private JPanel createKeypad() {
		JPanel panel = new JPanel(new BorderLayout());
		
		// creates the arm button
		JPanel armButtons = new JPanel();
		JButton stay = new JButton("Arm");
		stay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				station.arm();
				SimpliSafe.refresh();
				LED.setBackground(Color.RED);
			}
			
		});
		armButtons.add(stay);
		armButtons.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		// initializes the back button for access in below for-loop
		JButton backButton = new JButton("\u2190");
		backButton.setEnabled(false);
		
		// creates buttons for 1-9 and adds them to keypad
		JButton[] buttons = new JButton[10];
		JPanel numPanel = new JPanel(new GridLayout(4, 3));
		for (int i = 1; i < 10; i++) {
			JButton btn = new JButton("" + i);
			buttons[i] = btn;
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!backButton.isEnabled())
						backButton.setEnabled(true);
					textField.setText(textField.getText().concat(btn.getText()));
				}
			});
			numPanel.add(btn);
		}
		
		// creates the "back" button and adds it to keypad
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() == 1) 
					backButton.setEnabled(false);
				textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
			}
			
		});
		numPanel.add(backButton);
		
		// creates the "0" button and adds it to keypad
		JButton button0 = new JButton("0");
		button0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat(button0.getText()));
			}
			
		});
		numPanel.add(button0);
		
		// creates the "enter" button and adds it to keypad
		JButton enterButton = new JButton("\u2713");
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					station.disarm(Integer.valueOf(textField.getText()));
					SimpliSafe.refresh();
					LED.setBackground(Color.GREEN);
				} catch (IllegalArgumentException ex) {
					System.out.println("invalid input/system already disarmed");
				}
				textField.setText("");
			}
		});
		numPanel.add(enterButton);
		
		panel.add(armButtons, BorderLayout.NORTH);
		panel.add(numPanel);
		
		return panel;
	}
	
	/**
	 * Changes the LED's display state.
	 */
	private void flashLED() {
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
            LED.setBackground(Color.RED);
            LED.setVisible(true);
        }
	}
}