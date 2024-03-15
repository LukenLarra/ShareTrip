package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    private MainGUI mainGUI;

    @FXML
    private ToggleGroup userSignUp;
    @FXML
    private ToggleGroup userSignIp;

    @FXML
    private RadioButton driverSignUpBtn;

    @FXML
    private RadioButton travelerSignUpBtn;

    @FXML
    private RadioButton driverSignInBtn;

    @FXML
    private RadioButton travelerSignInBtn;

    @FXML
    private Label msgSignIn;

    @FXML
    private Label msgSignUp;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnShowSignIn;

    @FXML
    private Button btnShowSignUp;

    @FXML
    private TextField emailFieldSignIn;

    @FXML
    private TextField emailFieldSignUp;

    @FXML
    private PasswordField passwordFieldSignIn;

    @FXML
    private PasswordField passwordFieldSignUp;

    @FXML
    private TextField usernameFieldSignUp;

    @FXML
    void showSignIn(ActionEvent event) {
        mainGUI.showSignIn();
    }

    @FXML
    void showSignUp(ActionEvent event) {
        mainGUI.showSignUp();
    }

    @FXML
    void SignIn(ActionEvent event) {
        if (driverSignInBtn.isSelected()) {
            //get password and email from signIn fields
            //check if driver exists
            //if exists, show mainGUI.showMain();
            //else, show error message

        }else{
            //get password and email from signIn fields
            //check if traveler exists
            //if exists, show mainGUI.showMain();
            //else, show error message
        }
    }
    @FXML
    void SignUp(ActionEvent event) {
        if (driverSignUpBtn.isSelected()) {
            //get password, email and username from signUp fields
            //check if driver exists
            //if exists, show error message
            //else, create driver and show mainGUI.showMain();

        }else{
            //get password, email and username from signUp fields
            //check if traveler exists
            //if exists, show error message
            //else, create traveler and show mainGUI.showMain();
        }
    }
    @FXML
    void initialize() {

    }
}
