package model.sensor;

import model.BaseStation;

/**
 * Abstract epresentation of a sensor.
 * @author Blake Hamilton
 */
public abstract class Sensor {
	
	/**
	 * The BaseStation associated with the sensor.
	 */
	BaseStation station;
	
	/**
	 * The sensor's ID number.
	 */
	int ID;
	
	/**
	 * Creates a sensor with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the sensor.
	 * @param ID The sensors's ID number.
	 */
	Sensor(BaseStation station, int ID) {
		this.station = station;
		this.ID = ID;
	}
	
	/**
	 * Gets the BaseStation associated with the sensor.
	 * @return The BaseStation associated with the sensor.
	 */
	BaseStation getStation() {
		return station;
	}
	
	/**
	 * Sets the ID number of the sensors.
	 * @param ID The new ID number to assign to the sensor.
	 */
	void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * Gets the sensor's ID number.
	 * @return The sensor's ID number.
	 */
	int getID() {
		return ID;
	}
	
	/**
	 * Triggers the sensor.
	 */
	void trigger() {
		detect();
	}
	
	/**
	 * String representation of the sensor.
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
	
	/**
	 * Detects sensor related threat. Since we do not have access to the physical component, 
	 * it is implied that when this method is called the sensor has sensed its relative threat.
	 * @return The result of the detect method.
	 */
	public abstract String detect();
}