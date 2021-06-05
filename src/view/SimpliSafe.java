package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.BaseStation;

public class SimpliSafe {
	
	// list of keypads for refresh method
	public static ArrayList<KeypadGUI> keypadList = new ArrayList<KeypadGUI>();
    public static void main(String [] args) throws FileNotFoundException{
        BaseStation homeBase = new BaseStation();
        
        keypadList.add(new KeypadGUI(homeBase));
        keypadList.add(new KeypadGUI(homeBase));
        
        //new BaseStationGUI(homeBase);
    }
    
    public static void refresh() {
    	for (KeypadGUI keypad : keypadList) {
    		keypad.refresh();
    	}
    }
}