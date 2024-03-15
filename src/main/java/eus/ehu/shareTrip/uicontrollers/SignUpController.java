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

public class SignUpController implements Controller {

    private BlFacade businessLogic;

    @FXML
    private Button btnSignUp;

    @FXML
    private RadioButton driverSignUpBtn;

    @FXML
    private TextField emailFieldSignUp;

    @FXML
    private Label msgSignUp;

    @FXML
    private PasswordField passwordFieldSignUp;

    @FXML
    private RadioButton travelerSignUpBtn;

    @FXML
    private ToggleGroup userSignUp;

    @FXML
    private TextField usernameFieldSignUp;

    @FXML
    void SignUp(ActionEvent event) {

    }


    @Override
    public void setMainApp(MainGUI mainGUI) {

    }

    public SignUpController(BlFacade bl) {
        this.businessLogic = bl;
    }

}

