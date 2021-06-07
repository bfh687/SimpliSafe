package model.sensor;

import model.BaseStation;

/**
 * Representation of a water sensor.
 * @author Blake Hamilton
 */
public class WaterSensor extends Sensor {
	
	/**
	 * Count of how many current temperature sensors there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a water sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the water sensor.
	 * @param ID The water sensors's ID number.
	 */
	public WaterSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}
	
	/**
	 * {@inheritdoc}
	 * Detects water flooding. Since we do not have access to the physical component,
	 * it is implied that when this method is called the sensor has sensed water flooding.
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