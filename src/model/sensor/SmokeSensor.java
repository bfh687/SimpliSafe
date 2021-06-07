package model.sensor;

import model.BaseStation;

/**
 * Representation of a smoke detector/sensor.
 * @author Blake Hamilton
 */
public class SmokeSensor extends Sensor {
	
	/**
	 * Count of how many current smoke detectors/sensors there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a smoke detector/sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the smoke detector/sensor.
	 * @param ID The smoke detector's/sensors's ID number.
	 */
	public SmokeSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}
	
	/**
	 * {@inheritdoc}
	 * Detects smoke. Since we do not have access to the physical component, it is
	 * implied that when this method is called the sensor has sensed smoke.
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