package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.BaseStation;
import utility.FileUtility;
import view.BaseStationGUI;
import view.CameraGUI;
import view.KeypadGUI;
import view.PanicButtonGUI;
import view.TesterGUI;

/**
 * SimpliSafe application entry point. Initializes all GUIs and
 * system models representing SimpliSafe devices.
 * @author Blake Hamilton
 */
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
	 * List of panic button GUIs for the SimpliSafe system.
	 */
	public static ArrayList<PanicButtonGUI> panicList = new ArrayList<PanicButtonGUI>();
	
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
     * @throws FileNotFoundException If the file is not found.
     */
    public static void main(String [] args) throws FileNotFoundException {
    	File file = new File("src/model/config.txt");
        BaseStation homeBase = new BaseStation(file);
        loadFromFile(file, homeBase);
        
        testGUI = new TesterGUI(homeBase);
        baseGUI = new BaseStationGUI(homeBase);
        
        // write BaseStation config to file on close/restart
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
					FileUtility.writeToFile(homeBase, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }, "shutdown-thread"));
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
    
    /**
     * Loads and initializes device GUIs to the given BaseStation from the given file.
     * @param file File to load devices from.
     * @param station Station associated with the SimpliSafe system.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void loadFromFile(File file, BaseStation station) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		Scanner strScan = null;
		scan.nextLine(); // discard PIN
		while(scan.hasNextLine()) {
			strScan = new Scanner(scan.nextLine());
			
			String type = strScan.next();
			int ID = Integer.valueOf(strScan.next());
			
			switch(type) {
				case "Camera":
					cameraList.add(new CameraGUI(station, ID));
					break;
				case "PanicButton":
					panicList.add(new PanicButtonGUI(station, ID));
					break;
				case "Keypad":
					keypadList.add(new KeypadGUI(station, ID));
					break;
			}
		}
		scan.close();
		strScan.close();
	}
}