package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginControllerPSA implements Controller  {

    private MainGUI mainGUI;

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

    @FXML
    void initialize() {

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }


   /* @FXML
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

    */

}
