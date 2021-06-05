package model.sensor;

import model.BaseStation;

public class TemperatureSensor extends Sensor {
	public static int deviceCount = 0;
	
	public TemperatureSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}

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