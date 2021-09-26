package model.alarm;

/**
 * Representation of an alarm.
 * @author Blake Hamilton
 */
public class Alarm {
	
	/**
	 * Whether the alarm is currently active.
	 */
	private boolean active;
	
	/**
	 * Creates a new alarm object with default values.
	 */
	public Alarm() {
		active = false;
	}
	
	/**
	 * Triggers the alarm.
	 * @param device The device that triggered the alarm.
	 * @return The result of the function call.
	 */
	public String trigger(String device) {
		if (!active) {
			active = true;
			return "Alarm active, set off by: " + device;
		} else {
			return "Alarm already active";
		}
	}

	/**
	 * Disables the alarm.
	 */
	public void disable() {
		active = false;
	}
	
	/**
	 * Toggles the alarm.
	 */
	public void toggle() {
		active = !active;
	}
	
	/**
	 * Gets whether the alarm is currently active.
	 * @return Whether the alarm is currently active.
	 */
	public boolean isActive() {
		return active;
	}
}