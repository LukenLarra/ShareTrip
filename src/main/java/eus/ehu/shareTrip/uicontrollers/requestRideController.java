package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.ui.MainGUI;

public class requestRideController implements Controller{
    private MainGUI mainGUI;
    private BlFacade businessLogic;
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
