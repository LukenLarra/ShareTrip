package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.domain.Traveler;
import eus.ehu.shareTrip.domain.User;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

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
        if (emailFieldSignIn.getText().isEmpty() || passwordFieldSignIn.getText().isEmpty()) {
            msgSignIn.setText("Email or password cannot be empty.");
            msgSignIn.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignIn.setText("");
                msgSignIn.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        } else if (!emailFieldSignIn.getText().contains("@gmail.com")) {
            msgSignIn.setText("Email must be a gmail account.");
            msgSignIn.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignIn.setText("");
                msgSignIn.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        } else {
            try {
                User user = businessLogic.signIn(emailFieldSignIn.getText(), passwordFieldSignIn.getText());
                passwordFieldSignIn.clear();
                emailFieldSignIn.clear();
                msgSignIn.setText("Logged in successfully.");
                msgSignIn.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-background-radius: 5px; -fx-text-radius: 5px;");
                businessLogic.setCurrentUser(user);


                getSingUpBtn().setVisible(false);
                getSingInBtn().setVisible(false);
                getLogoutBtn().setVisible(true);
                getQueryRidesBtn().setVisible(true);
                if (user instanceof Driver){
                    getCreateRidesBtn().setVisible(true);
                    ((MainGUIController)mainGUI.getMainWin().getController()).showScene("CreateRide");
                }
                else if (user instanceof Traveler){
                    getMyRidesBtn().setVisible(true);
                    ((MainGUIController)mainGUI.getMainWin().getController()).showScene("QueryRides");
                }


                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    msgSignIn.setText("");
                    msgSignIn.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
                });
                pause.play();
            } catch (Exception exception) {
                msgSignIn.setText("Email or password incorrect. Make sure you are signed up.");
                msgSignIn.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    msgSignIn.setText("");
                    msgSignIn.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
                });
                pause.play();
            }
        }
    }

    @Override @FXML
    public void keyboardNav(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            SignIn(new ActionEvent());
        }
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public SignInController(BlFacade bl) {
        this.businessLogic = bl;
    }
    @Override
    public Node getLogoutBtn() {
        return mainGUI.getMainGUI().getLogoutBtn();
    }
    @Override
    public Node getQueryRidesBtn() {
        return mainGUI.getMainGUI().getQueryRidesBtn();
    }
    @Override
    public Node getCreateRidesBtn() {
        return mainGUI.getMainGUI().getCreateRidesBtn();
    }
    @Override
    public Node getMyRidesBtn() {
        return mainGUI.getMainGUI().getMyRidesBtn();
    }

    @Override
    public Node getSingUpBtn() {
        return mainGUI.getMainGUI().getSingUpBtn();
    }

    @Override
    public Node getSingInBtn() {
        return mainGUI.getMainGUI().getSingInBtn();
    }
}



