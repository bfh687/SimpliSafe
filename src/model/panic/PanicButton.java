package model.panic;

import model.BaseStation;

public class PanicButton {
	public static int deviceCount = 0;
	
	private BaseStation station;
	private int ID;
	
	public PanicButton(BaseStation station, int ID) {
		this.station = station;
		this.ID = ID;
		deviceCount++;
	}
	
	public void panic() {
		station.triggerAlarm(toString());
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
}