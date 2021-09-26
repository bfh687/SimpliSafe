package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.BaseStation;
import model.camera.Camera;
import model.panic.PanicButton;
import model.sensor.Sensor;

/**
 * Utility class used for writing configuration file.
 * @author Blake Hamilton
 */
public class FileUtility {
	
	/**
	 * New-line regex character.
	 */
	public static final String NEW_LINE = "\n";
	
	/**
	 * Writes the devices associated with the BaseStation to the given file.
	 * @param station The station to save to file.
	 * @param file The file to save the station's configuration to.
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static void writeToFile(BaseStation station, File file) throws IOException {
		
		FileWriter writer = new FileWriter(file);
		writer.write(station.getPIN() + NEW_LINE);
		
		// keypads are a constant
		writer.write("Keypad 1" + NEW_LINE);
		writer.write("Keypad 2" + NEW_LINE);
		
		int counter = 1;
		
		for (Camera camera : station.getCameras()) {
			writer.write("Camera " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (PanicButton panic : station.getPanicButtons()) {
			writer.write("PanicButton " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getEntrySensorList()) {
			writer.write("EntrySensor " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getWaterSensorList()) {
			writer.write("WaterSensor " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getGlassSensorList()) {
			writer.write("GlassSensor " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getSmokeSensorList()) {
			writer.write("SmokeSensor " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getMotionSensorList()) {
			writer.write("MotionSensor " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getCarbonMonoList()) {
			writer.write("CarbonMonoxideSensor " + counter++ + NEW_LINE);
		}
		
		counter = 1;
		for (Sensor sensor : station.getTemperatureSensorList()) {
			writer.write("TemperatureSensor " + counter++ + NEW_LINE);
		}
		
		writer.close();
	}
}