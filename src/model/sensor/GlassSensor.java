package model.sensor;

import model.BaseStation;

/**
 * Representation of a glass-break sensor.
 * @author Blake Hamilton
 */
public class GlassSensor extends Sensor {
	
	/**
	 * Count of how many current glass-break sensors there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a glass-break sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the glass-break sensor.
	 * @param ID The glass-break sensors's ID number.
	 */
	public GlassSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}
	
	/**
	 * {@inheritdoc}
	 * Detects glass-break. Since we do not have access to the physical component, it is
	 * implied that when this method is called the sensor has sensed glass breaking.
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