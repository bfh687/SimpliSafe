package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.BaseStation;
import model.camera.Camera;
import model.sensor.Sensor;

public class MobileApp extends JFrame {
    /**
     *
     */
    private static final int WIDTH = 500;
    private static final int LENGTH = 500;
    private static final long serialVersionUID = 4835892900024045580L;
    private BaseStation station;
    private Sensor[] sensors;
    private Camera[] cameras;
    private JTextField textField;
    private JFrame frame;

    public MobileApp(BaseStation station) {
        this.station = station;
        init();
    }

    private void init() {
        frame = new JFrame("Mobile Application");
        frame.setSize(new Dimension(WIDTH, LENGTH));
        frame.setMinimumSize(new Dimension(WIDTH, LENGTH));

        JPanel cameraPanel = new JPanel();
        cameraPanel.setLayout(new FlowLayout());

        final JLabel cameraLabel = new JLabel("Cameras:");
        cameraPanel.add(cameraLabel);

        JComboBox<Camera> cameraDropDown = createCameraDropDown();
        cameraPanel.add(cameraDropDown);

        JButton cameraButton =new JButton("Check Steam");
        cameraPanel.add(cameraButton);

        frame.add(cameraPanel);

        JPanel sensorPanel = new JPanel();
        sensorPanel.setLayout(new FlowLayout());

        final JLabel sensorLabel = new JLabel("Sensors:");
        sensorPanel.add(sensorLabel);

        JComboBox<Sensor> sensorDropDown = createSensorDropDown();
        sensorPanel.add(sensorDropDown);

        JButton b =new JButton("Check Status");
        sensorPanel.add(b);

        frame.add(sensorPanel);

        cameraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CameraGUI feed = new CameraGUI(station,
                        cameraDropDown.getItemAt(cameraDropDown.getSelectedIndex()).getID());
            }
        });

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sensorDropDown.getItemAt(cameraDropDown.getSelectedIndex()).showStatus();
            }
        });

//		JPanel textFieldPanel = createTextField();
//		textFieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		panel.add(textFieldPanel, BorderLayout.NORTH);
//
//		JPanel keypadPanel = createKeypad();
//		keypadPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
//
//		panel.add(keypadPanel, BorderLayout.CENTER);

        pack();
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JComboBox<Sensor> createSensorDropDown() {
        List<Sensor> carbonMonoSensors = station.getCarbonMonoList();
        List<Sensor> entrySensors = station.getEntrySensorList();
        List<Sensor> temperatureSensors= station.getTemperatureSensorList();
        List<Sensor> motionSensors = station.getMotionSensorList();
        List<Sensor> glassSensors = station.getGlassSensorList();
        List<Sensor> waterSensors = station.getWaterSensorList();
        List<Sensor> smokeSensors = station.getSmokeSensorList();


        sensors = new Sensor[carbonMonoSensors.size() + entrySensors.size()
                + temperatureSensors.size() + motionSensors.size() + glassSensors.size()
                + waterSensors.size() + smokeSensors.size() + 1];
        int i = 0;
        for (Sensor sensor : carbonMonoSensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : carbonMonoSensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : entrySensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : temperatureSensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : motionSensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : glassSensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : waterSensors) {
            sensors[i] = sensor;
            i++;
        }
        for (Sensor sensor : smokeSensors) {
            sensors[i] = sensor;
            i++;
        }

        return new JComboBox<Sensor>(sensors);

    }

    private JComboBox<Camera> createCameraDropDown(){
        List<Camera> cameraList = station.getCameras();
        cameras = new Camera[cameraList.size() + 1];
        int i = 0;
        for (Camera camera : cameraList) {
            cameras[i] = camera;
            i++;
        }
        return new JComboBox<Camera>(cameras);
    }

//	public void init() {
//		setSize(new Dimension(300, 450));
//		setMinimumSize(new Dimension(300, 450));
//
//		JPanel panel = new JPanel();
//		panel.setLayout(new BorderLayout());
//		add(panel);
//
//		JPanel textFieldPanel = createTextField();
//		textFieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		panel.add(textFieldPanel, BorderLayout.NORTH);
//
//		JPanel keypadPanel = createKeypad();
//		keypadPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
//
//		panel.add(keypadPanel, BorderLayout.CENTER);
//
//		pack();
//		setVisible(true);
//		setResizable(false);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	public JPanel createTextField() {
//		JPanel panel = new JPanel(new BorderLayout());
//		textField = new JTextField(10);
//		textField.setEditable(false);
//		panel.add(textField);
//
//		LED = new JPanel();
//		LED.setBackground(Color.GREEN);
//		panel.add(LED, BorderLayout.EAST);
//
//		return panel;
//	}
//
//	public JPanel createKeypad() {
//		JPanel panel = new JPanel(new BorderLayout());
//
//		// --------------------------------------------------
//		JPanel armButtons = new JPanel();
//
//		JButton stay = new JButton("Arm");
//		stay.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				station.arm();
//				LED.setBackground(Color.RED);
//			}
//
//		});
//
//		armButtons.add(stay);
//
//		armButtons.setBorder(new EmptyBorder(0, 0, 10, 0));
//		// --------------------------------------------------
//
//		// --------------------------------------------------
//		JPanel numPanel = new JPanel(new GridLayout(4, 3));
//
//		JButton[] buttons = new JButton[10];
//
//		final JButton backButton = new JButton("\u2190");
//		backButton.setEnabled(false);
//
//		for (int i = 1; i < 10; i++) {
//			JButton btn = new JButton("" + i);
//			buttons[i] = btn;
//			System.out.println(btn.getText());
//			btn.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					if (!backButton.isEnabled())
//						backButton.setEnabled(true);
//					textField.setText(textField.getText().concat(btn.getText()));
//				}
//
//			});
//			numPanel.add(btn);
//		}
//
//		backButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (textField.getText().length() == 1)
//					backButton.setEnabled(false);
//				textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
//			}
//
//		});
//		numPanel.add(backButton);
//
//		JButton button0 = new JButton("0");
//		button0.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				textField.setText(textField.getText().concat(button0.getText()));
//			}
//
//		});
//		numPanel.add(button0);
//
//		JButton enterButton = new JButton("\u2713");
//		enterButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					station.disarm(Integer.valueOf(textField.getText()));
//				} catch (IllegalArgumentException ex) {
//					System.out.println("invalid input");
//				}
//			}
//
//		});
//		numPanel.add(enterButton);
//		// --------------------------------------------------
//
//		panel.add(armButtons, BorderLayout.NORTH);
//		panel.add(numPanel);
//
//		return panel;
//	}

}