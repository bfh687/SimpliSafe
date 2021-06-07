package model.camera;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Representation of a security camera.
 * @author Blake Hamilton
 */
public class Camera {
	
	/**
	 * Count of how many current cameras there are across the system.
	 */
	public static int deviceCount = 0;
	
	/**
	 * Image representing an active camera.
	 */
	private BufferedImage activeImage;
	
	/**
	 * Image representing an inactive camera.
	 */
	private BufferedImage nullImage;
	
	/**
	 * The camera's ID number.
	 */
	private int ID;
	
	/**
	 * Whether the camera is active.
	 */
	private boolean active;
	
	/**
	 * Creates a camera with the given ID.
	 * @param ID The ID of the camera.
	 */
	public Camera(int ID) {
		active = true;
		
		// loads active/inactive camera images from file
		try {
			activeImage = ImageIO.read(new File("src/model/camera/placeholder.jpg"));
			nullImage = ImageIO.read(new File("src/model/camera/placeholdernull.jpg"));
		} catch(Exception e) {
			System.out.println("Failed to load image");
		}
		this.ID = ID;
		deviceCount++;
	}
	
	/**
	 * Enables the camera.
	 */
	public void enable() {
		active = true;
	}
	
	/**
	 * Disables the camera.
	 */
	public void disable() {
		active = false;
	}
	
	/**
	 * Toggles the camera.
	 */
	public void toggle() {
		active = !active;
	}
	
	/**
	 * Gets the camera's ID.
	 * @return The camera's ID.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Returns the camera's current view.
	 * @return The camera's current view.
	 */
	public BufferedImage stream() {
		if (active) {
			return activeImage;
		} else {
			return nullImage;
		}
	}
	
	
	/**
	 * {@inheritdoc}
	 * String representation of the camera.
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
}
