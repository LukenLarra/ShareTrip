package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SignInController implements Controller {

    private MainGUI mainGUI;

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
        if (driverSignInBtn.isSelected()) {
            if (businessLogic.signInDriver(emailFieldSignIn.getText(), passwordFieldSignIn.getText())) {
                Stage stage = (Stage) btnSignIn.getScene().getWindow();
                stage.close();
                mainGUI.showMain();

            } else {
                msgSignIn.setText("Email or password incorrect. Make sure you are signed up.");
            }
        } else {
            if (businessLogic.signInTraveler(emailFieldSignIn.getText(), passwordFieldSignIn.getText())) {
                Stage stage = (Stage) btnSignIn.getScene().getWindow();
                stage.close();
                mainGUI.showMain();
            } else {
                msgSignIn.setText("Email or password incorrect. Make sure you are signed up");
            }
        }
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public SignInController(BlFacade bl) {
        this.businessLogic = bl;
    }
}



