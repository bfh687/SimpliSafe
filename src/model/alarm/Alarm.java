package model.alarm;

public class Alarm {
	private boolean active;
	
	public Alarm() {
		active = false;
	}
	
	// if already active, do nothing
	public String trigger(String device) {
		if (!active) {
			active = true;
			return "ALARM ACTIVE LOUD SOUNDS, SET OFF BY:" + device;
		} else {
			return "alarm already active";
		}
	}

	
	public void disable() {
		active = false;
	}
	
	public void toggle() {
		active = !active;
	}
	
	public boolean isActive() {
		return active;
	}
}