package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SignInController implements Controller {

    private BlFacade businessLogic;

    @FXML
    private Button btnSignIn;

    @FXML
    private RadioButton driverSignInBtn;

    @FXML
    private TextField emailFieldSignIn;

    @FXML
    private Label msgSignIn;

    @FXML
    private PasswordField passwordFieldSignIn;

    @FXML
    private RadioButton travelerSignInBtn;

    @FXML
    private ToggleGroup userSignIn;

    @FXML
    void SignIn(ActionEvent event) {

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
    }

    public SignInController(BlFacade bl) {
        this.businessLogic = bl;
    }
}



