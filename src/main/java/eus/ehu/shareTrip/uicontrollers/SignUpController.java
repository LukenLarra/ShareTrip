package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class SignUpController implements Controller {

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField emailFieldSignUp;

    @FXML
    private ToggleGroup userSignUp;

    @FXML
    private Label msgSignUp;

    @FXML
    private PasswordField passwordFieldSignUp;

    @FXML
    private PasswordField passwordChecker;

    @FXML
    private TextField usernameFieldSignUp;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button profileImageBtn;

    private String imagePath;

    @FXML
    void SignUp(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) userSignUp.getSelectedToggle();
        if (emailFieldSignUp.getText().isEmpty() || usernameFieldSignUp.getText().isEmpty() || passwordFieldSignUp.getText().isEmpty()) {
            msgSignUp.setText("Please fill all the fields");
            msgSignUp.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignUp.setText("");
                msgSignUp.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        } else if (!emailFieldSignUp.getText().toLowerCase().contains("@gmail.com")) {
            msgSignUp.setText("Email must be a gmail account.");
            msgSignUp.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignUp.setText("");
                msgSignUp.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        } else if (businessLogic.existsUser(emailFieldSignUp.getText())) {
            msgSignUp.setText("This email is already in use. Choose another one.");
            msgSignUp.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignUp.setText("");
                msgSignUp.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        }else if (!(passwordFieldSignUp.getText().equals(passwordChecker.getText()))){
            msgSignUp.setText("Passwords do not match");
            msgSignUp.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignUp.setText("");
                msgSignUp.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        } else {
            businessLogic.signUp(emailFieldSignUp.getText(), usernameFieldSignUp.getText(), passwordFieldSignUp.getText(), selectedRadioButton.getText(), imagePath);
            msgSignUp.setText("User created successfully");
            userSignUp.selectToggle(null);
            emailFieldSignUp.clear();
            usernameFieldSignUp.clear();
            passwordFieldSignUp.clear();
            passwordChecker.clear();
            msgSignUp.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-background-radius: 5px; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                msgSignUp.setText("");
                msgSignUp.setStyle("-fx-text-fill: none; -fx-background-color: transparent; -fx-background-radius: none; -fx-text-radius: none;");
            });
            pause.play();
        }
    }



    @FXML
    void chooseProfileImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
            profileImage.setImage(new Image("file:///" + imagePath));
        }
    }

    @FXML
    public void keyEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            SignUp(new ActionEvent());
            event.consume();
        }
    }


    @FXML
    void initialize() {
        imagePath = "../../../../images/defaultProfile.jpg";

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public SignUpController(BlFacade bl) {
        this.businessLogic = bl;
    }

    @Override
    public Node getLogoutBtn() {
        return null;
    }

    @Override
    public Node getQueryRidesBtn() {
        return null;
    }

    @Override
    public Node getCreateRidesBtn() {
        return null;
    }
    @Override
    public Node getMyRidesBtn() {
        return null;
    }

    @Override
    public Node getSingUpBtn() {
        return null;
    }

    @Override
    public Node getSingInBtn() {
        return null;
    }

    public TextField getEmailTF() { return emailFieldSignUp; }

}

