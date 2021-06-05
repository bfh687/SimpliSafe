package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BaseStation;

public class mobileApp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4835892900024045580L;
	private BaseStation station;
	private JTextField textField;
	private JPanel LED;
	
	public KeypadGUI(BaseStation station) {
		this.station = station;
		init();
	}
	
	public void init() {
		setSize(new Dimension(300, 450));
		setMinimumSize(new Dimension(300, 450));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		
		JPanel textFieldPanel = createTextField();
		textFieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
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
	
	public JPanel createTextField() {
		JPanel panel = new JPanel(new BorderLayout());
		textField = new JTextField(10);
		textField.setEditable(false);
		panel.add(textField);

		LED = new JPanel();
		LED.setBackground(Color.GREEN);
		panel.add(LED, BorderLayout.EAST);
		
		return panel;
	}
	
	public JPanel createKeypad() {
		JPanel panel = new JPanel(new BorderLayout());
		
		// --------------------------------------------------
		JPanel armButtons = new JPanel();
		
		JButton stay = new JButton("Arm");
		stay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				station.arm();
				LED.setBackground(Color.RED);
			}
			
		});
		
		armButtons.add(stay);
		
		armButtons.setBorder(new EmptyBorder(0, 0, 10, 0));
		// --------------------------------------------------
		
		// --------------------------------------------------
		JPanel numPanel = new JPanel(new GridLayout(4, 3));
		
		JButton[] buttons = new JButton[10];
		
		final JButton backButton = new JButton("\u2190");
		backButton.setEnabled(false);
		
		for (int i = 1; i < 10; i++) {
			JButton btn = new JButton("" + i);
			buttons[i] = btn;
			System.out.println(btn.getText());
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
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() == 1) 
					backButton.setEnabled(false);
				textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
			}
			
		});
		numPanel.add(backButton);
		
		JButton button0 = new JButton("0");
		button0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat(button0.getText()));
			}
			
		});
		numPanel.add(button0);
		
		JButton enterButton = new JButton("\u2713");
		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					station.disarm(Integer.valueOf(textField.getText()));
				} catch (IllegalArgumentException ex) {
					System.out.println("invalid input");
				}
			}
			
		});
		numPanel.add(enterButton);
		// --------------------------------------------------
		
		panel.add(armButtons, BorderLayout.NORTH);
		panel.add(numPanel);
		
		return panel;
	}
	
}