package test.sensor;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import model.BaseStation;
import model.sensor.EntrySensor;
import model.sensor.Sensor;

public class SensorTest {
	
	Sensor sensor;
	BaseStation station;
	
	@Before
	public void setUp() throws Exception {
		File file = new File("src/model/config.txt");
		station = new BaseStation(file);
		sensor = new EntrySensor(station, 0);
	}

	@Test
	public void testSensor() throws FileNotFoundException {
		File file = new File("src/model/config.txt");
		station = new BaseStation(file);
		Sensor sensor = new EntrySensor(station, 0);
		
		assertNotNull(file);
		assertNotNull(station);
		assertEquals(0, sensor.getID());
	}

	@Test
	public void testGetStation() {
		assertNotNull(sensor.getStation());
	}

	@Test
	public void testGetID() {
		assertEquals(0, sensor.getID());
	}

	@Test
	public void testToString() {
		assertEquals("[EntrySensor0]", sensor.toString());
	}

	@Test
	public void testDetect() {
		station.arm();
		sensor.detect();
		assertTrue(station.getAlarm().isActive());
		
		station.disarm(1234);
		assertEquals("Station is not armed and/or alarm is already active", sensor.detect());
	}

}
