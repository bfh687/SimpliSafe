package model.sensor;

import model.BaseStation;

/**
 * Representation of a carbon monoxide sensor.
 * @author Blake Hamilton
 */
public class CarbonMonoxideSensor extends Sensor {
	
	/**
	 * Count of how many current panic buttons there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Creates a carbon monoxide sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the carbon monoxide sensor.
	 * @param ID The carbon monoxide sensors's ID number.
	 */
	public CarbonMonoxideSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}

	/**
	 * {@inheritdoc}
	 * Detects carbon monoxide. Since we do not have access to the physical component,
	 * it is implied that when this method is called the sensor has sensed carbon monoxide.
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
