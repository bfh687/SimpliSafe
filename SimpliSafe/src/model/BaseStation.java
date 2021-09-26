package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.alarm.Alarm;
import model.camera.Camera;
import model.panic.PanicButton;
import model.sensor.*;

/**
 * The base station of our SimpliSafe system.
 * @author Blake Hamilton
 * @author Alibile Ugas
 * @author Lynda Tanielu
 */
public class BaseStation {
	
	/**
	 * The alarm associated with the BaseStation.
	 */
	private Alarm alarm;
	
	/**
	 * The list of carbon monoxide sensors associated with the BaseStation.
	 */
	private List<Sensor> carbonMonoList;
	
	/**
	 * The list of carbon entry sensors associated with the BaseStation.
	 */
	private List<Sensor> entrySensorList;
	
	/**
	 * The list of carbon temperature sensors associated with the BaseStation.
	 */
	private List<Sensor> temperatureSensorList;
	
	/**
	 * The list of carbon motion sensors associated with the BaseStation.
	 */
	private List<Sensor> motionSensorList;
	
	/**
	 * The list of glass-break sensors associated with the BaseStation.
	 */
	private List<Sensor> glassSensorList;
	
	/**
	 * The list of carbon water sensors associated with the BaseStation.
	 */
	private List<Sensor> waterSensorList;
	
	/**
	 * The list of carbon smoke detectors/sensors associated with the BaseStation.
	 */
	private List<Sensor> smokeSensorList;

	/**
	 * The list of all cameras associated with the BaseStation.
	 */
	private List<Camera> cameraList;
	
	/**
	 * The list of all panic buttons associated with the BaseStation.
	 */
	private List<PanicButton> panicList;
	
	/**
	 * The PIN associated with the BaseStation.
	 */
	private int PIN;
	
	/**
	 * Whether the BaseStation is armed.
	 */
	private boolean armed; 
	
	/**
	 * Creates a BaseStation from the given configuration file.
	 * @param file The file to load the BaseStation from.
	 * @throws FileNotFoundException If file is not found.
	 */
	public BaseStation(File file) throws FileNotFoundException {
		
		// initialize all device lists
		carbonMonoList = new ArrayList<Sensor>();
		entrySensorList = new ArrayList<Sensor>();
		temperatureSensorList = new ArrayList<Sensor>();
		motionSensorList = new ArrayList<Sensor>();
		glassSensorList = new ArrayList<Sensor>();
		waterSensorList = new ArrayList<Sensor>();
		smokeSensorList = new ArrayList<Sensor>();
		cameraList = new ArrayList<Camera>();
		panicList = new ArrayList<PanicButton>();
		
		// load devices from configuration file
		try {
			loadConfig(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to load config from file.");
		}
		
		// set default state of system and create alarm
		armed = false;
		alarm = new Alarm();
	}
	
	/**
	 * Arms the system.
	 */
	public void arm() {
		armed = true;
	}
	
	/**
	 * Attempts to disarm the system using the given PIN.
	 * @param PIN The PIN for the system.
	 */
	public void disarm(int PIN) {
		if (this.PIN == PIN && isArmed()) {
			armed = false;
			if (alarm.isActive()) {
				alarm.disable();
			}
		} else {
			throw new IllegalArgumentException("Incorrect PIN or system already disarmed.");
		}
	}
	
	/**
	 * Triggers the system's alarm.
	 * @param device The device that triggered the alarm.
	 * @return Device that triggered the alarm.
	 */
	public String triggerAlarm(String device) {
		return alarm.trigger(device);
	}
	
	/**
	 * Adds a sensor to the system.
	 * @param sensor The sensor to be added to the system.
	 */
	public void addSensor(Sensor sensor) {
		if (sensor instanceof EntrySensor) {
			entrySensorList.add(new EntrySensor(this, sensor.getID()));
		} else if (sensor instanceof MotionSensor) {
			motionSensorList.add(new MotionSensor(this, sensor.getID()));
		} else if (sensor instanceof GlassSensor) {
			glassSensorList.add(new GlassSensor(this, sensor.getID()));
		} else if (sensor instanceof SmokeSensor) {
			smokeSensorList.add(new SmokeSensor(this, sensor.getID()));
		} else if (sensor instanceof TemperatureSensor) {
			temperatureSensorList.add(new TemperatureSensor(this, sensor.getID()));
		} else if (sensor instanceof WaterSensor) {
			waterSensorList.add(new WaterSensor(this, sensor.getID()));
		} else if (sensor instanceof CarbonMonoxideSensor) {
			carbonMonoList.add(new CarbonMonoxideSensor(this, sensor.getID()));
		}
	}
	
	/**
	 * Removes a sensor from the system.
	 * @param sensor The sensor to be removed from the system.
	 */
	public void removeSensor(Sensor sensor) {
		if (sensor instanceof EntrySensor) {
			entrySensorList.remove(sensor);
		} else if (sensor instanceof MotionSensor) {
			motionSensorList.remove(sensor);
		} else if (sensor instanceof GlassSensor) {
			glassSensorList.remove(sensor);
		} else if (sensor instanceof SmokeSensor) {
			smokeSensorList.remove(sensor);
		} else if (sensor instanceof TemperatureSensor) {
			temperatureSensorList.remove(sensor);
		} else if (sensor instanceof WaterSensor) {
			waterSensorList.remove(sensor);
		} else if (sensor instanceof CarbonMonoxideSensor) {
			carbonMonoList.remove(sensor);
		}
	}
	
	/**
	 * Adds a camera to the system.
	 * @param camera The camera to be added to the system.
	 */
	public void addCamera(Camera camera) {
		cameraList.add(camera);
	}
	
	/**
	 * Removes a camera from the system.
	 * @param camera The camera to be removed from the system.
	 */
	public void removeCamera(Camera camera) {
		cameraList.remove(camera);
	}
	
	/**
	 * Adds a panic button to the system.
	 * @param panicButton The panic button to be added to the system.
	 */
	public void addPanicButton(PanicButton panicButton) {
		panicList.add(panicButton);
	}
	
	/**
	 * Removes a panic button from the system.
	 * @param panicButton The panic button to be removed from the system.
	 */
	public void removePanicButton(PanicButton panicButton) {
		panicList.remove(panicButton);
	}
	
	/**
	 * Returns whether the system is armed or not.
	 * @return Whether the system is armed or not.
	 */
	public boolean isArmed() {
		return armed;
	}
	
	/**
	 * Sets the PIN for the system.
	 * @param PIN The PIN to assign to the system.
	 */
	public void setPIN(int PIN) {
		this.PIN = PIN;
	}
	
	/**
	 * Gets the PIN for the system.
	 * @return The PIN for the system.
	 */
	public int getPIN() {
		return PIN;
	}
	
	/**
	 * Gets a list of all carbon monoxide sensors in the system.
	 * @return A list of all carbon monoxide sensors in the system.
	 */
	public List<Sensor> getCarbonMonoList() {
		return carbonMonoList;
	}
	
	/**
	 * Gets a list of all entry sensors in the system.
	 * @return A list of all entry sensors in the system.
	 */
	public List<Sensor> getEntrySensorList() {
		return entrySensorList;
	}
	
	/**
	 * Gets a list of all temperature sensors in the system.
	 * @return A list of all temperature sensors in the system.
	 */
	public List<Sensor> getTemperatureSensorList() {
		return temperatureSensorList;
	}
	
	/**
	 * Gets a list of all motion sensors in the system.
	 * @return A list of all motion sensors in the system.
	 */
	public List<Sensor> getMotionSensorList() {
		return motionSensorList;
	}
	
	/**
	 * Gets a list of all glass-break sensors in the system.
	 * @return A list of all glass-break sensors in the system.
	 */
	public List<Sensor> getGlassSensorList() {
		return glassSensorList;
	}
	
	/**
	 * Gets a list of all water sensors in the system.
	 * @return A list of all water sensors in the system.
	 */
	public List<Sensor> getWaterSensorList() {
		return waterSensorList;
	}
	
	/**
	 * Gets a list of all smoke detectors/sensors in the system.
	 * @return A list of all smoke detectors/sensors in the system.
	 */
	public List<Sensor> getSmokeSensorList() {
		return smokeSensorList;
	}
	
	/**
	 * Gets a list of all cameras in the system.
	 * @return A list of all cameras in the system.
	 */
	public List<Camera> getCameras() {
		return cameraList;
	}
	
	/**
	 * Gets a list of all panic buttons in the system.
	 * @return A list of all panic buttons in the system.
	 */
	public List<PanicButton> getPanicButtons() {
		return panicList;
	}
	
	/**
	 * Gets the alarm associated with the system.
	 * @return The alarm associated with the system.
	 */
	public Alarm getAlarm() {
		return alarm;
	}
	
	/**
	 * Loads and initializes all devices associated with the 
	 * base station using the given config file.
	 * @param file The config file.
	 * @throws FileNotFoundException If file does not exist.
	 */
	public void loadConfig(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		Scanner strScan = null;
		if (scan.hasNextLine())
			PIN = Integer.valueOf(scan.nextLine());
		while(scan.hasNextLine()) {
			strScan = new Scanner(scan.nextLine());
			String type = strScan.next();
			int ID = Integer.valueOf(strScan.next());
			
			// add sensor to system based on type from config
			switch(type) {
				case "EntrySensor":
					addSensor(new EntrySensor(this, ID));
					break;
				case "MotionSensor":
					addSensor(new MotionSensor(this, ID));
					break;
				case "GlassSensor":
					addSensor(new GlassSensor(this, ID));
					break;
				case "SmokeSensor":
					addSensor(new SmokeSensor(this, ID));
					break;
				case "TemperatureSensor":
					addSensor(new TemperatureSensor(this, ID));
					break;
				case "WaterSensor":
					addSensor(new WaterSensor(this, ID));
					break;
				case "CarbonMonoxideSensor":
					addSensor(new CarbonMonoxideSensor(this, ID));
				    break;
				case "Camera":
					cameraList.add(new Camera(ID));
					break;
				case "PanicButton":
					panicList.add(new PanicButton(this, ID));
					break;
			}
		}
		scan.close();
		strScan.close();
	}
}