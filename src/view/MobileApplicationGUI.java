package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import model.BaseStation;
import model.camera.Camera;
import model.sensor.Sensor;

/**
 * Mobile application GUI.
 * @author Troy Zon
 */
public class MobileApplicationGUI extends JFrame {
	
    /**
     * The width of the applcation.
     */
    private static final int WIDTH = 500;
    
    /**
     * The height of the applcation.
     */
    private static final int LENGTH = 500;
    
    /**
     * The generated serial version UID.
     */
    private static final long serialVersionUID = 4835892900024045580L;
    
    /**
     * The BaseStation associated with the mobile application.
     */
    private BaseStation station;
    
    /**
     * All sensors in the system.
     */
    private Sensor[] sensors;
    
    /**
     * All cameras in the system.
     */
    private Camera[] cameras;
    
    /**
     * The applications frame.
     */
    private JFrame frame;
    
    /**
     * Creates a mobile application GUI.
     * @param station The BaseStation associated with the mobile application.
     */
    public MobileApplicationGUI(BaseStation station) {
        this.station = station;
        init();
    }
    
    /**
   	 * Initializes all GUI components.
   	 */
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

        JButton cameraButton =new JButton("Check Stream");
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
                feed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sensorDropDown.getItemAt(cameraDropDown.getSelectedIndex()).showStatus();
            }
        });

        pack();
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Creates the sensor drop down component.
     * @return JComboBox of all sensors.
     */
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
    
    /**
     * Creates the camera drop down component.
     * @return JComboBox of all cameras.
     */
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
}