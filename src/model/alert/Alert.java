package model.alert;

public class Alert {
	private String deviceName;
	
	public Alert(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String toString() {
		// change to current time in US time format
		return deviceName + System.currentTimeMillis();
	}
}
