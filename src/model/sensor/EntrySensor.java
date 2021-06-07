package model.sensor;

import model.BaseStation;

/**
 * Representation of an entry sensor.
 * @author Blake Hamilton
 */
public class EntrySensor extends Sensor {
	
	/**
	 * Count of how many current entry sensors there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a entry sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the entry sensor.
	 * @param ID The entry sensors's ID number.
	 */
	public EntrySensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}

	/**
	 * {@inheritdoc}
	 * Detects entry. Since we do not have access to the physical component, it is
	 * implied that when this method is called the sensor has sensed entry.
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
