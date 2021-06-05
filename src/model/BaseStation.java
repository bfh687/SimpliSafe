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
	private Alarm alarm;
	private int PIN;
	private List<Sensor> sensorList;
	private List<Camera> cameraList;
	private List<PanicButton> panicList;
	private boolean armed;
	
	// add new File config thing
	public BaseStation(Alarm alarm) throws FileNotFoundException {
		// initialize all devices here
		sensorList = new ArrayList<Sensor>();
		cameraList = new ArrayList<Camera>();
		panicList = new ArrayList<PanicButton>();
		File file = new File("C:\\Users\\seshb\\Documents\\WINTER 21\\WORKSPACE\\SimpliSafe\\src\\model\\config");
		try {
			loadConfig(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to load config from file.");
		}
		
		this.alarm = alarm;
		armed = false;
	}
	
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
			}
		}
		scan.close();
		strScan.close();
	}
	
	public void arm() {
		armed = true;
	}
	
	public void disarm(int PIN) {
		if (this.PIN == PIN) {
			armed = false;
		} else {
			throw new IllegalArgumentException("Incorrect PIN");
		}
	}
	
	public void toggle() {
		armed = !armed;
	}
    
	public void triggerAlarm(String device) {
		alarm.trigger(device);
	}
	
	public void addSensor(Sensor sensor) {
		sensorList.add(sensor);
	}
	
	public void removeSensor(Sensor sensor) {
		sensorList.remove(sensor);
	}
	
	public void addCamera(Camera camera) {
		cameraList.add(camera);
	}
	
	public void removeCamera(Camera camera) {
		cameraList.remove(camera);
	}
	
	public void addPanicButton(PanicButton panicButton) {
		panicList.add(panicButton);
	}
	
	public void removePanicButton(PanicButton panicButton) {
		panicList.remove(panicButton);
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public void setPIN(int PIN) {
		this.PIN = PIN;
	}
	
	public int getPIN() {
		return PIN;
	}
	
	public List<Sensor> getSensors() {
		return sensorList;
	}
	
	public List<Camera> getCameras() {
		return cameraList;
	}
	
	public List<PanicButton> getPanicButtons() {
		return panicList;
	}
	
	public Alarm getAlarm() {
		return alarm;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Alarm alarm = new Alarm();
		BaseStation station = new BaseStation(alarm);
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