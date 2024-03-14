package eus.ehu.shareTrip.uicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    @FXML
    private ToggleGroup User;
    @FXML
    private RadioButton driverBtn;
    @FXML
    private Label msgLogin;
    @FXML
    private RadioButton travelerBtn;
    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void SignIn(ActionEvent event) {
        if (driverBtn.isSelected()) {

        }else{

        }
    }
    @FXML
    void SignUp(ActionEvent event) {
        if (driverBtn.isSelected()) {

        }else{

        }
    }
    @FXML
    void initialize() {

    }
}
