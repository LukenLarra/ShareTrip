package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.User;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController implements Controller {

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField emailFieldSignIn;

    @FXML
    private Label msgSignIn;

    @FXML
    private PasswordField passwordFieldSignIn;

    @FXML
    void SignIn(ActionEvent event) {
        if(emailFieldSignIn.getText().contains("@gmail.com")) {
            User user = businessLogic.signIn(emailFieldSignIn.getText(), passwordFieldSignIn.getText());
            if (user == null) {
                msgSignIn.setText("Email or password incorrect. Make sure you are signed up.");
            }else{
                passwordFieldSignIn.clear();
                emailFieldSignIn.clear();
                msgSignIn.setText("Logged in successfully.");
                businessLogic.setCurrentUser(user);
            }
        } else {
            msgSignIn.setText("Email must be a gmail account.");
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



