package model.camera;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Camera {
	public static int deviceCount = 0;

	private BufferedImage activeImage;
	private BufferedImage nullImage;
	private int ID;
	private boolean active;
	
	public Camera(int ID) {
		active = true;
		try {
			activeImage = ImageIO.read(new File("src/model/camera/placeholder.jpg"));
			nullImage = ImageIO.read(new File("src/model/camera/placeholdernull.jpg"));
		} catch(Exception e) {
			System.out.println("Failed to load image");
		}
		this.ID = ID;
		deviceCount++;
	}
	
	public void enable() {
		active = true;
	}
	
	public void disable() {
		active = false;
	}
	
	public void toggle() {
		active = !active;
		System.out.println("active = " + active);
	}
	
	public BufferedImage stream() {
		if (active) {
			return activeImage;
		} else {
			return nullImage;
		}
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ID + "]";
	}
}
