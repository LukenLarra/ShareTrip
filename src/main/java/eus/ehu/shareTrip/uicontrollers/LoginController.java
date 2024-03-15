package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements Controller {

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    @FXML
    private Button btnShowSignIn;

    @FXML
    private Button btnShowSignUp;

    @FXML
    void showSignIn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(mainGUI.getSignInWin(), 600, 500));
        stage.show();
    }

    @FXML
    void showSignUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(mainGUI.getSignUpWin(), 600, 500));
        stage.show();
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public LoginController(BlFacade bl) {
        this.businessLogic = bl;
    }


}