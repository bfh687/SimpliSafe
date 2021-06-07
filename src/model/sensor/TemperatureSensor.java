package model.sensor;

import model.BaseStation;

/**
 * Representation of a temperature sensor.
 * @author Blake Hamilton
 */
public class TemperatureSensor extends Sensor {
	
	/**
	 * Count of how many current temperature sensors there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a temperature sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the temperature sensor.
	 * @param ID The temperature sensors's ID number.
	 */
	public TemperatureSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}
	
	/**
	 * {@inheritDoc}
	 * Detects freezing temperatures. Since we do not have access to the physical component,
	 * it is implied that when this method is called the sensor has sensed a freezing temperature.
	 */
	@Override 
	public String detect() {
		// detect sensor specific threat via hardware here
		if (station.isArmed() && !station.getAlarm().isActive())
			return station.triggerAlarm(toString());
		else {
			return "Station is not armed and/or alarm is already active";
		}
	}
}