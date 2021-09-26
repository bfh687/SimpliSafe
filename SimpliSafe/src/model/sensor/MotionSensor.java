package model.sensor;

import model.BaseStation;

/**
 * Representation of a motion sensor.
 * @author Blake Hamilton
 */
public class MotionSensor extends Sensor {
	
	/**
	 * Count of how many current motion sensors there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a motion sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the motion sensor.
	 * @param ID The motion sensors's ID number.
	 */
	public MotionSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}
	
	/**
	 * {@inheritDoc}
	 * Detects motion. Since we do not have access to the physical component, it is
	 * implied that when this method is called the sensor has sensed motion.
	 */
	@Override 
	public String detect() {
		// detect sensor specific threat via hardware here
		if (station.isArmed() && !station.getAlarm().isActive()) 
			return station.triggerAlarm(toString());
		else
			return "Station is not armed and/or alarm is already active";
	}
}