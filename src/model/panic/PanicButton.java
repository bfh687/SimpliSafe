package model.panic;

import model.BaseStation;

/**
 * Representation of a panic button.
 * @author Blake Hamilton
 */
public class PanicButton {
	
	/**
	 * Count of how many current panic buttons there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * The BaseStation associated with the panic button.
	 */
	private BaseStation station;
	
	/**
	 * The ID of the panic button.
	 */
	private int ID;
	
	/**
	 * Creates a panic button with the given ID, and associated with the given BaseStation.
	 * @param station The BaseStation associated with the panic button.
	 * @param ID The panic button's ID number.
	 */
	public PanicButton(BaseStation station, int ID) {
		this.station = station;
		this.ID = ID;
		deviceCount++;
	}
	
	/**
	 * Immediately turns on the alarm, regardless of whether the system is armed or not.
	 */
	public void panic() {
		station.arm();
		station.triggerAlarm(toString());
	}
	
	/**
	 * Gets the panic button's ID number.
	 * @return The panic button's ID number.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * {@inheritDoc}
	 * String representation of the panic button.
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
}