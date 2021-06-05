package view;

import java.io.FileNotFoundException;

import model.BaseStation;

public class SimpliSafe {
    public static void main(String [] args) throws FileNotFoundException{
        BaseStation homeBase = new BaseStation();
        new KeypadGUI(homeBase);
        //new BaseStationGUI(homeBase);
    }
}