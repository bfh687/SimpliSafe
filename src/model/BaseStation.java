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

public class BaseStation {
	/**
	 * The alarm associated with the BaseStation.
	 */
	private Alarm alarm;
	
	/**
	 * The list of all sensors associated with the BaseStation.
	 */
	private List<Sensor> sensorList;
	
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
	 * Creates a BaseStation from the given config file.
	 * @throws FileNotFoundException
	 */
	public BaseStation(/*File file????*/) throws FileNotFoundException {
		sensorList = new ArrayList<Sensor>();
		cameraList = new ArrayList<Camera>();
		panicList = new ArrayList<PanicButton>();
		File file = new File("src/model/config");
		try {
			loadConfig(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to load config from file.");
		}
		armed = false;
		alarm = new Alarm();
	}
	
	/**
	 * Loads and initializes all devices associated with the 
	 * base station using the given config file.
	 * @param file The config file.
	 * @throws FileNotFoundException
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

			switch(type) {
				case "EntrySensor":
					sensorList.add(new EntrySensor(this, ID));
					break;
				case "MotionSensor":
					sensorList.add(new MotionSensor(this, ID));
					break;
				case "GlassSensor":
					sensorList.add(new GlassSensor(this, ID));
					break;
				case "SmokeSensor":
					sensorList.add(new SmokeSensor(this, ID));
					break;
				case "TemperatureSensor":
					sensorList.add(new TemperatureSensor(this, ID));
					break;
				case "WaterSensor":
					sensorList.add(new WaterSensor(this, ID));
					break;
				case "Camera":
					cameraList.add(new Camera(ID));
					break;
				case "PanicButton":
					panicList.add(new PanicButton(this, ID));
					break;
				case "CarbonMonoxideSensor":
				    sensorList.add(new CarbonMonoxideSensor(this, ID));
				    break;
			}
		}
		scan.close();
		strScan.close();
	}
	
	/**
	 * Arms the system.
	 */
	public void arm() {
		armed = true;
		System.out.println("armed system");
	}
	
	/**
	 * Attempts to disarm the system using the given PIN.
	 * @param PIN The PIN for the system.
	 */
	public void disarm(int PIN) {
		if (this.PIN == PIN && isArmed()) {
			armed = false;
			System.out.println("disarmed system");
		} else {
			throw new IllegalArgumentException("Incorrect PIN or system already disarmed.");
		}
	}
	
	/**
	 * Triggers the system's alarm,
	 * @param device The device that triggered the alarm.
	 */
	public void triggerAlarm(String device) {
		alarm.trigger(device);
	}
	
	/**
	 * Adds a sensor to the system.
	 * @param sensor The sensor to be added to the system.
	 */
	public void addSensor(Sensor sensor) {
		sensorList.add(sensor);
	}
	
	/**
	 * Removes a sensor from the system.
	 * @param sensor The sensor to be removed from the system.
	 */
	public void removeSensor(Sensor sensor) {
		sensorList.remove(sensor);
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
	 * Gets a list of all sensors in the system.
	 * @return A list of all sensors in the system.
	 */
	public List<Sensor> getSensors() {
		return sensorList;
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
	 * The application's entry point. Used for testing the base station.
	 * @param args Command-line arguements.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		BaseStation station = new BaseStation();
		//int defaultPIN = 1234;
		
		station.getSensors().get(0).detect();
		station.arm();
		station.getSensors().get(0).detect();
		System.out.println();
		
		System.out.println(station.getCameras().get(0));
		System.out.println(station.getCameras().get(0).stream());
		station.getCameras().get(0).disable();
		System.out.println(station.getCameras().get(0).stream() + "\n");
		
		station.getPanicButtons().get(0).panic();
		
		System.out.println(station.getSensors());
		System.out.println(station.getCameras());
		System.out.println(station.getPanicButtons());
	}
}