package model.sensor;

import model.BaseStation;

public class EntrySensor extends Sensor {
	public static int deviceCount = 0;
	
	public EntrySensor(BaseStation station, int ID) {
		super(station, ID);
		deviceCount++;
	}

	@Override
	public void detect() {
		// detect sensor specific threat via hardware here
		if (station.isArmed() && !station.getAlarm().isActive()) 
			station.triggerAlarm(toString());
	}
}
