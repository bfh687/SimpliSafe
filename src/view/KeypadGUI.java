package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BaseStation;
import model.alarm.Alarm;

public class KeypadGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4835892900024045580L;
	private BaseStation station;
	private JTextField textField;
	
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
		JPanel panel = new JPanel();
		textField = new JTextField(10);
		textField.setEditable(false);
		panel.add(textField);
		return panel;
	}
	
	public JPanel createKeypad() {
		JPanel panel = new JPanel(new GridLayout(4, 3));
		
		JButton[] buttons = new JButton[12];
		
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
			panel.add(btn);
		}
		
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() == 1) 
					backButton.setEnabled(false);
				textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
			}
			
		});
		panel.add(backButton);
		
		JButton button0 = new JButton("0");
		button0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText().concat(button0.getText()));
			}
			
		});
		panel.add(button0);
		
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
		panel.add(enterButton);

		return panel;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// replace file
		new KeypadGUI(new BaseStation(new Alarm()));
	}
	
}