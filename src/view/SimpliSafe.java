package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.BaseStation;
import model.BaseStationGUI;

public class SimpliSafe {
	
	// list of keypads for refresh method
	public static ArrayList<KeypadGUI> keypadList = new ArrayList<KeypadGUI>();
    public static void main(String [] args) throws FileNotFoundException{
        BaseStation homeBase = new BaseStation();
        
        KeypadGUI keypad1 = new KeypadGUI(homeBase);
        KeypadGUI keypad2 = new KeypadGUI(homeBase);
        keypadList.add(keypad1);
        keypadList.add(keypad2);
        
        new BaseStationGUI(homeBase);
        new TesterGUI(homeBase);
    }
    
    public static void refresh() {
    	for (KeypadGUI keypad : keypadList) {
    		keypad.refresh();
    	}
    }
}