package test.sensor;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import model.BaseStation;
import model.sensor.TemperatureSensor;

public class TemperatureSensorTest {
	
	private TemperatureSensor sensor;
	private BaseStation station;

	@Before
	public void setUp() throws Exception {
		File file = new File("src/model/config.txt");
		station = new BaseStation(file);
		sensor = new TemperatureSensor(station, 0);
	}

	@Test
	public void testDetect() {
		station.arm();
		sensor.detect();
		assertTrue(station.getAlarm().isActive());
		
		station.disarm(1234);
		assertEquals("Station is not armed and/or alarm is already active", sensor.detect());
	}

	@Test
	public void testTemperatureSensor() throws FileNotFoundException {
		File file = new File("src/model/config.txt");
		BaseStation station = new BaseStation(file);
		TemperatureSensor sensor = new TemperatureSensor(station, 0);
		
		assertNotNull(file);
		assertNotNull(station);
		assertEquals(0, sensor.getID());
	}

}
