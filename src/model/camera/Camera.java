package model.camera;

import java.awt.Image;

public class Camera {
	public static int deviceCount = 0;
	
	// video feed
	private Image image;
	private int ID;
	private boolean active;
	
	public Camera(int ID) {
		active = true;
		image = null; // placeholder image
		this.ID = ID;
	}
	
	public void enable() {
		System.out.println("enabled camera");
		active = true;
	}
	
	public void disable() {
		System.out.println("disabled camera");
		active = false;
	}
	
	public void toggle() {
		active = !active;
	}
	
	// returns a placeholder image of the "videostream"
	public Image stream() {
		if (active) {
			System.out.println("Returned video stream image (enabled)");
			return image;
		} else {
			System.out.println("Returned black stream image (disabled)");
			return image; // black placeholder image
		}
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
}
