package model.sensor;

import model.BaseStation;

public class MotionSensor extends Sensor {
	public static int deviceCount = 0;
	
	public MotionSensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}

	@Override 
	public void detect() {
		// detect sensor specific threat via hardware here
		if (station.isArmed() && !station.getAlarm().isActive()) 
			station.triggerAlarm(toString());
		else
			System.out.println("Station is not armed and/or alarm is already active");
	}
}