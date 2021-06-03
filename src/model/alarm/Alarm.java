package model.alarm;

public class Alarm {
	private boolean active;
	
	public Alarm() {
		active = false;
	}
	
	public void trigger(String device) {
		active = true;
		System.out.println("ALARM ACTIVE LOUD SOUNDS, SET OFF BY:" + device);
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