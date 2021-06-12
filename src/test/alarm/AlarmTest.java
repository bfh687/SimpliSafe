package test.alarm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.alarm.Alarm;

public class AlarmTest {
	
	private Alarm alarm;
	
	@Before
	public void setUp() throws Exception {
		alarm = new Alarm();
	}

	@Test
	public void testAlarm() {
		Alarm alarm = new Alarm();
		assertFalse(alarm.isActive());
	}

	@Test
	public void testTrigger() {
		alarm.trigger(null);
		assertTrue(alarm.isActive());
	}

	@Test
	public void testDisable() {
		alarm.disable();
		assertFalse(alarm.isActive());
	}

	@Test
	public void testToggle() {
		boolean state = alarm.isActive();
		alarm.toggle();
		
		if (state) 
			assertFalse(alarm.isActive());
		else 
			assertTrue(alarm.isActive());
	}

	@Test
	public void testIsActive() {
		alarm.disable();
		assertFalse(alarm.isActive());
		
		alarm.trigger(null);
		assertTrue(alarm.isActive());
	}

}
