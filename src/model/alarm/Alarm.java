package model.alarm;

public class Alarm {
	private boolean active;
	
	public Alarm() {
		active = false;
	}
	
	// if already active, do nothing
	public void trigger(String device) {
		if (!active) {
			active = true;
			System.out.println("ALARM ACTIVE LOUD SOUNDS, SET OFF BY:" + device);
		} else {
			System.out.println("alarm already active");
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