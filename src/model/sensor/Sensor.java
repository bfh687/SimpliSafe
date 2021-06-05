package model.sensor;

import model.BaseStation;

public abstract class Sensor {
	BaseStation station;
	int ID;
	
	// takes the station that the sensor is attached to
	Sensor(BaseStation station, int ID) {
		this.station = station;
		this.ID = ID;
	}
	
	BaseStation getStation() {
		return station;
	}
	
	void setID(int ID) {
		this.ID = ID;
	}
	
	int getID() {
		return ID;
	}
	
	// used for testing
	void trigger() {
		detect();
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
	
	public abstract String detect();
}