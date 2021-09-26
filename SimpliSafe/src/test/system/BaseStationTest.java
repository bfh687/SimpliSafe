package test.system;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import model.BaseStation;
import model.camera.Camera;
import model.panic.PanicButton;
import model.sensor.CarbonMonoxideSensor;
import model.sensor.EntrySensor;
import model.sensor.GlassSensor;
import model.sensor.MotionSensor;
import model.sensor.SmokeSensor;
import model.sensor.TemperatureSensor;
import model.sensor.WaterSensor;

public class BaseStationTest {
	
	private BaseStation station;

	@Before
	public void setUp() throws Exception {
		File file = new File("src/model/config.txt");
		station = new BaseStation(file);
	}

	@Test
	public void testBaseStation() throws FileNotFoundException {
		File file = new File("src/model/config.txt");
		BaseStation station = new BaseStation(file);
		
		assertNotNull(file);
		assertNotNull(station);
		
		// check sensor initialization
		assertNotNull(station.getCarbonMonoList());
		assertNotNull(station.getEntrySensorList());
		assertNotNull(station.getTemperatureSensorList());
		assertNotNull(station.getMotionSensorList());
		assertNotNull(station.getGlassSensorList());
		assertNotNull(station.getWaterSensorList());
		assertNotNull(station.getSmokeSensorList());
		
		// check camera/panic initialization
		assertNotNull(station.getCameras());
		assertNotNull(station.getPanicButtons());

		assertFalse(station.isArmed());
		assertNotNull(station.getAlarm());
	}

	@Test
	public void testArm() {
		station.arm();
		assertTrue(station.isArmed());
	}

	@Test
	public void testDisarm() {
		station.arm();
		station.disarm(1234);
		assertFalse(station.isArmed());
	}

	@Test
	public void testTriggerAlarm() {
		station.triggerAlarm(null);
		assertTrue(station.getAlarm().isActive());
	}
	
	@Test 
	public void testAddSensor() {
		int size;
		
		size = station.getCarbonMonoList().size() + 1;
		station.addSensor(new CarbonMonoxideSensor(station, 4));
		assertEquals(size, station.getCarbonMonoList().size());
		
		size = station.getEntrySensorList().size() + 1;
		station.addSensor(new EntrySensor(station, 4));
		assertEquals(size, station.getEntrySensorList().size());
		
		size = station.getTemperatureSensorList().size() + 1;
		station.addSensor(new TemperatureSensor(station, 4));
		assertEquals(size, station.getTemperatureSensorList().size());
		
		size = station.getMotionSensorList().size() + 1;
		station.addSensor(new MotionSensor(station, 4));
		assertEquals(size, station.getMotionSensorList().size());

		size = station.getSmokeSensorList().size() + 1;
		station.addSensor(new SmokeSensor(station, 4));
		assertEquals(size, station.getSmokeSensorList().size());
		
		size = station.getGlassSensorList().size() + 1;
		station.addSensor(new GlassSensor(station, 4));
		assertEquals(size, station.getGlassSensorList().size());
		
		size = station.getWaterSensorList().size() + 1;
		station.addSensor(new WaterSensor(station, 4));
		assertEquals(size, station.getWaterSensorList().size());
	}
	
	public void testRemoveSensor() {
		int size;
		
		size = Math.max(station.getCarbonMonoList().size() - 1, 0);
		station.removeSensor(new CarbonMonoxideSensor(station, 4));
		assertEquals(size, station.getCarbonMonoList().size());
		
		size = Math.max(station.getEntrySensorList().size() - 1, 0);
		station.removeSensor(new EntrySensor(station, 4));
		assertEquals(size, station.getEntrySensorList().size());
		
		size = Math.max(station.getTemperatureSensorList().size() - 1, 0);
		station.removeSensor(new TemperatureSensor(station, 4));
		assertEquals(size, station.getTemperatureSensorList().size());
		
		size = Math.max(station.getMotionSensorList().size() - 1, 0);
		station.removeSensor(new MotionSensor(station, 4));
		assertEquals(size, station.getMotionSensorList().size());

		size = Math.max(station.getSmokeSensorList().size() - 1, 0);
		station.removeSensor(new SmokeSensor(station, 4));
		assertEquals(size, station.getSmokeSensorList().size());
		
		size = Math.max(station.getGlassSensorList().size() - 1, 0);
		station.removeSensor(new GlassSensor(station, 4));
		assertEquals(size, station.getGlassSensorList().size());
		
		size = Math.max(station.getWaterSensorList().size() - 1, 0);
		station.removeSensor(new WaterSensor(station, 4));
		assertEquals(size, station.getWaterSensorList().size());
	}

	@Test
	public void testAddCamera() {
		int size = station.getCameras().size() + 1;
		station.addCamera(new Camera(0));
		assertEquals(size, station.getCameras().size());
	}

	@Test
	public void testRemoveCamera() {
		int size = station.getCameras().size() - 1;
		station.removeCamera(station.getCameras().get(0));
		assertEquals(size, station.getCameras().size());
	}

	@Test
	public void testAddPanicButton() {
		int size = station.getPanicButtons().size() + 1;
		station.addPanicButton(new PanicButton(station, 0));
		assertEquals(size, station.getPanicButtons().size());
	}

	@Test
	public void testRemovePanicButton() {
		int size = station.getPanicButtons().size() - 1;
		station.removePanicButton(station.getPanicButtons().get(0));
		assertEquals(size, station.getPanicButtons().size());
	}

	@Test
	public void testIsArmed() {
		station.arm();
		assertTrue(station.isArmed());
		
		station.disarm(1234);
		assertFalse(station.isArmed());
	}

	@Test
	public void testSetPIN() {
		station.setPIN(6789);
		assertEquals(6789, station.getPIN());
	}

	@Test
	public void testGetPIN() {
		assertEquals(1234, station.getPIN());
	}

	@Test
	public void testGetCarbonMonoList() {
		assertNotNull(station.getCarbonMonoList());
	}

	@Test
	public void testGetEntrySensorList() {
		assertNotNull(station.getEntrySensorList());
	}

	@Test
	public void testGetTemperatureSensorList() {
		assertNotNull(station.getTemperatureSensorList());
	}

	@Test
	public void testGetMotionSensorList() {
		assertNotNull(station.getMotionSensorList());
	}

	@Test
	public void testGetGlassSensorList() {
		assertNotNull(station.getGlassSensorList());
	}

	@Test
	public void testGetWaterSensorList() {
		assertNotNull(station.getWaterSensorList());
	}

	@Test
	public void testGetSmokeSensorList() {
		assertNotNull(station.getSmokeSensorList());
	}

	@Test
	public void testGetCameras() {
		assertNotNull(station.getCameras());
	}

	@Test
	public void testGetPanicButtons() {
		assertNotNull(station.getPanicButtons());
	}

	@Test
	public void testGetAlarm() {
		assertNotNull(station.getAlarm());
	}
}