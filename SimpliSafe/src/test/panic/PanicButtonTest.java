package test.panic;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import model.BaseStation;
import model.panic.PanicButton;

public class PanicButtonTest {
	
	private PanicButton panic;
	private BaseStation station;
	
	@Before
	public void setUp() throws Exception {
		File file = new File("src/model/config.txt");
		station = new BaseStation(file);
		panic = new PanicButton(station, 0);
	}

	@Test
	public void testPanicButton() throws Exception {
		File file = new File("src/model/config.txt");
		BaseStation station = new BaseStation(file);
		PanicButton panic = new PanicButton(station, 0);
		
		assertNotNull(file);
		assertNotNull(station);
		assertEquals(0, panic.getID());
	}

	@Test
	public void testPanic() {
		panic.panic();
		assertTrue(station.getAlarm().isActive());
	}

	@Test
	public void testGetID() {
		assertEquals(0, panic.getID());
	}

	@Test
	public void testToString() {
		assertEquals("[PanicButton0]", panic.toString());
	}

}
