package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController implements Controller {

    private BlFacade businessLogic;

    @FXML
    private Button btnShowSignIn;

    @FXML
    private Button btnShowSignUp;

    @FXML
    void showSignIn(ActionEvent event) {

    }

    @FXML
    void showSignUp(ActionEvent event) {

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {

    }

    public LoginController(BlFacade bl) {
        this.businessLogic = bl;
    }


}