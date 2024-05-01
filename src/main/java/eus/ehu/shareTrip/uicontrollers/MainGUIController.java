package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.domain.Traveler;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUIController implements Controller{


    @FXML
    private BorderPane mainWrapper;

    @FXML
    private Label selectOptionLbl;

    @FXML
    private Button queryRidesBtn;

    @FXML
    private Button createRidesBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnShowSignIn;

    @FXML
    private Button btnShowSignUp;

    @FXML
    private Label lblUserName;

    @FXML
    private Button myRidesBtn;

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    public MainGUIController(){};

    public MainGUIController(BlFacade blFacade){
        businessLogic = blFacade;
    }

    public Labeled getLblUsername() {
        return lblUserName;
    }


    public class Window {
        private Controller controller;
        private Parent ui;
    }

    private Window load(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent ui = loader.load();
            Controller controller = loader.getController();

            Window window = new Window();
            window.controller = controller;
            window.ui = ui;
            return window;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void queryRides(ActionEvent event) {
        showScene("QueryRides");
    }

    @FXML
    void createRide(ActionEvent event) {
        showScene("CreateRide");
    }


    @FXML
    void showSignIn(ActionEvent event) throws IOException {
        showScene("SignIn");
    }
    @FXML
    void showSignUp(ActionEvent event) throws IOException {
        //if the driver has already signed in don't let them sign up again
        if (businessLogic.getCurrentUser() != null) {
            selectOptionLbl.setText("You are already signed in.");
            selectOptionLbl.setStyle("-fx-text-fill: red; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> selectOptionLbl.setText(""));
            pause.play();
        } else {
            showScene("SignUp");
        }
    }

    @FXML
    void logout(ActionEvent event) {
        businessLogic.logout();
        selectOptionLbl.setText("You have been logged out.");
        selectOptionLbl.setStyle("-fx-text-fill: green; -fx-text-radius: 5px;");

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> selectOptionLbl.setText(""));
        pause.play();
        getLogoutBtn().setVisible(false);
        getQueryRidesBtn().setVisible(false);
        getCreateRidesBtn().setVisible(false);
        getMyRidesBtn().setVisible(false);
        getSingUpBtn().setVisible(true);
        getSingInBtn().setVisible(true);
        ((MainGUIController)mainGUI.getMainWindow().getController()).getLblUsername().setText("");
        showScene("SignIn");
    }

    @FXML
    void myRides(ActionEvent event) {
        if (businessLogic.getCurrentUser() instanceof Driver) {
            showScene("MyRidesDriver");
        } else {
            showScene("MyRidesTraveler");
        }
    }


    @FXML
    void initialize() {
        logoutBtn.setVisible(false);
        queryRidesBtn.setVisible(false);
        createRidesBtn.setVisible(false);
        myRidesBtn.setVisible(false);
    }

    public void showScene(String scene) {
        switch (scene) {
            case "CreateRide" -> {
                mainWrapper.setCenter(mainGUI.getCreateRidesWin());
                ((CreateRideController)(mainGUI.getCreateRidesWindow().getController())).refreshRideRequests(new ActionEvent());
            }
            case "QueryRides" -> mainWrapper.setCenter(mainGUI.getQueryRidesWin());
            case "SignIn" -> mainWrapper.setCenter(mainGUI.getSignInWin());
            case "SignUp" -> mainWrapper.setCenter(mainGUI.getSignUpWin());
            case "MyRidesTraveler" -> {
                mainWrapper.setCenter(mainGUI.getMyRidesTravelerWin());
                ((MyRidesTravelerController)(mainGUI.getMyRidesTravelerWindow().getController())).refreshMyRidesTraveler(new ActionEvent());
                ((MyRidesTravelerController)(mainGUI.getMyRidesTravelerWindow().getController())).showProfileDetails();
                ((MyRidesTravelerController)(mainGUI.getMyRidesTravelerWindow().getController())).showProfileImage();
            }
            case "MyRidesDriver" -> {
                mainWrapper.setCenter(mainGUI.getMyRidesDriverWin());
                ((MyRidesDriverController)(mainGUI.getMyRidesDriverWindow().getController())).refreshMyRidesDriver(new ActionEvent());
                ((MyRidesDriverController)(mainGUI.getMyRidesDriverWindow().getController())).showProfileDetails();
                ((MyRidesDriverController)(mainGUI.getMyRidesDriverWindow().getController())).showProfileImage();
            }
        }
    }

    @FXML
    public void signUpTAB(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            showScene("SignUp");
        }
        if (event.getCode() == KeyCode.TAB){
            btnShowSignIn.requestFocus();
            event.consume();
        }
    }

    @FXML
    public void signInTAB(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            showScene("SignIn");
        }
        if (event.getCode() == KeyCode.TAB) {
            if (businessLogic.getCurrentUser() instanceof Driver) {
                createRidesBtn.requestFocus();
                event.consume();
            } else if (businessLogic.getCurrentUser() instanceof Traveler) {
                queryRidesBtn.requestFocus();
                event.consume();
            } else {
                if (mainWrapper.getCenter() == mainGUI.getSignInWin()) {
                    ((SignInController)(mainGUI.getSignInWindow().getController())).getEmailTF().requestFocus();
                } else if (mainWrapper.getCenter() == mainGUI.getSignUpWin()) {
                    ((SignUpController)(mainGUI.getSignUpWindow().getController())).getEmailTF().requestFocus();
                }
                event.consume();
            }
        }
    }

    @FXML
    public void createRidesTAB(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB){
            queryRidesBtn.requestFocus();
            event.consume();
        }
    }

    @FXML
    public void queryRidesTAB(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB){
            myRidesBtn.requestFocus();
            event.consume();
        }
    }

    @FXML
    public void myRidesTAB(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB){
            logoutBtn.requestFocus();
            event.consume();
        }
    }

    @FXML
    public void logOutTAB(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB){
            if (mainWrapper.getCenter() == mainGUI.getSignInWin()) {
                ((SignInController)(mainGUI.getSignInWindow().getController())).getEmailTF().requestFocus();
            } else if (mainWrapper.getCenter() == mainGUI.getSignUpWin()) {
                ((SignUpController)(mainGUI.getSignUpWindow().getController())).getEmailTF().requestFocus();
            } else if (mainWrapper.getCenter() == mainGUI.getCreateRidesWin()) {
                ((CreateRideController)(mainGUI.getCreateRidesWindow().getController())).getDatePicker().requestFocus();
            } else if (mainWrapper.getCenter() == mainGUI.getQueryRidesWin()) {
                ((QueryRidesController)(mainGUI.getQueryRidesWindow().getController())).getComboDepartCity().requestFocus();
            } else if (mainWrapper.getCenter() == mainGUI.getMyRidesTravelerWin()) {
                ((MyRidesTravelerController)(mainGUI.getMyRidesTravelerWindow().getController())).getSelectImageButton().requestFocus();
            } else if (mainWrapper.getCenter() == mainGUI.getMyRidesDriverWin()) {
                ((MyRidesDriverController)(mainGUI.getMyRidesDriverWindow().getController())).getSelectImageButton().requestFocus();
            }
            event.consume();
        }
    }

    public Node getLogoutBtn() {
        return logoutBtn;
    }

    public Node getQueryRidesBtn() {
        return queryRidesBtn;
    }

    public Node getCreateRidesBtn() {
        return createRidesBtn;
    }

    public Node getMyRidesBtn() {
        return myRidesBtn;
    }

    public Node getSingUpBtn() {
        return btnShowSignUp;
    }

    public Node getSingInBtn() {
        return btnShowSignIn;
    }


    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
