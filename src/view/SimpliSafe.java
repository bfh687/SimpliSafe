package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.BaseStation;
import model.BaseStationGUI;

public class SimpliSafe {
	
	/**
	 * List of keypad GUIs for the SimpliSafe system.
	 */
	public static ArrayList<KeypadGUI> keypadList = new ArrayList<KeypadGUI>();
	
	/**
	 * List of camera GUIs for the SimpliSafe system.
	 */
	public static ArrayList<CameraGUI> cameraList = new ArrayList<CameraGUI>();
	
	/**
	 * BaseStation GUI for the SimpliSafe system.
	 */
    public static BaseStationGUI baseGUI;
    
    /**
     * Tester GUI for the SimpliSafe system.
     */
    public static TesterGUI testGUI;
    
    /**
     * SimpliSafe security system entry point.
     * @param args Command-line arguments.
     * @throws FileNotFoundException
     */
    public static void main(String [] args) throws FileNotFoundException{
        BaseStation homeBase = new BaseStation();
        
        keypadList.add(new KeypadGUI(homeBase));
        keypadList.add(new KeypadGUI(homeBase));
        
        cameraList.add(new CameraGUI(homeBase));
        cameraList.add(new CameraGUI(homeBase));

        testGUI = new TesterGUI(homeBase);
        baseGUI = new BaseStationGUI(homeBase);
    }
    
    /**
     * Refreshes all GUIs so that they all reflect the BaseStation's state.
     */
    public static void refresh() {
    	for (KeypadGUI keypad : keypadList) {
    		keypad.refresh();
    	}
    	for (CameraGUI camera : cameraList) {
    		camera.refresh();
    	}
    	baseGUI.refresh();
    }
}