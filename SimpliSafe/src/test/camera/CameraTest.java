package test.camera;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.camera.Camera;

public class CameraTest {
	
	private Camera camera;
	
	@Before
	public void setUp() throws Exception {
		camera = new Camera(0);
	}

	@Test
	public void testCamera() {
		Camera camera = new Camera(1);
		assertTrue(camera.isActive());
		
		camera.enable();
		assertNotNull(camera.stream());
		
		camera.disable();
		assertNotNull(camera.stream());
		
		assertEquals(1, camera.getID());
	}

	@Test
	public void testEnable() {
		camera.enable();
		assertTrue(camera.isActive());
	}

	@Test
	public void testDisable() {
		camera.disable();
		assertFalse(camera.isActive());
	}

	@Test
	public void testToggle() {
		boolean state = camera.isActive();
		camera.toggle();
		
		if (state) 
			assertFalse(camera.isActive());
		else 
			assertTrue(camera.isActive());
	}

	@Test
	public void testGetID() {
		assertEquals(0, camera.getID());
	}

	@Test
	public void testStream() {
		assertNotNull(camera.stream());
	}

	@Test
	public void testToString() {
		assertEquals("[Camera0]", camera.toString());
	}

}
