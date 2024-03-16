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

    private MainGUI mainGUI;

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
    private TextField usernameFieldSignUp;

    @FXML
    void SignUp(ActionEvent event) {
        if (driverSignUpBtn.isSelected()) {
            if (emailFieldSignUp.getText().isEmpty() || usernameFieldSignUp.getText().isEmpty() || passwordFieldSignUp.getText().isEmpty()) {
                msgSignUp.setText("Please fill all the fields");
            }else if (businessLogic.existsDriver(emailFieldSignUp.getText())) {
                msgSignUp.setText("This email is already in use. Choose another one.");
            }else {
                businessLogic.signUpDriver(emailFieldSignUp.getText(), usernameFieldSignUp.getText(), passwordFieldSignUp.getText());
                msgSignUp.setText("Driver created successfully");
            }

        }else {
            if (emailFieldSignUp.getText().isEmpty() || usernameFieldSignUp.getText().isEmpty() || passwordFieldSignUp.getText().isEmpty()) {
                msgSignUp.setText("Please fill all the fields");
            }else if (businessLogic.existsTraveler(emailFieldSignUp.getText())) {
                msgSignUp.setText("This email is already in use. Choose another one.");
            }else {
                businessLogic.signUpTraveler(emailFieldSignUp.getText(), usernameFieldSignUp.getText(), passwordFieldSignUp.getText());
                msgSignUp.setText("Traveler created successfully");
            }

        }
    }


    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public SignUpController(BlFacade bl) {
        this.businessLogic = bl;
    }

}

