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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
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

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    public MainGUIController(){};

    public MainGUIController(BlFacade blFacade){
        businessLogic = blFacade;
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
         if (businessLogic.getCurrentUser().getClass().equals(Traveler.class)){
            selectOptionLbl.setText("Only drivers can create rides.");
            selectOptionLbl.setStyle("-fx-text-fill: red; -fx-text-radius: 5px;");
            showScene("QueryRides");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> selectOptionLbl.setText(""));
            pause.play();
        }else {
            showScene("CreateRide");
        }
    }


    @FXML
    void showSignIn(ActionEvent event) throws IOException {
        //if the driver has already signed in don't let them sign in again
        if (businessLogic.getCurrentUser() != null) {
            selectOptionLbl.setText("You are already signed in.");
            selectOptionLbl.setStyle("-fx-text-fill: red; -fx-text-radius: 5px;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> selectOptionLbl.setText(""));
            pause.play();
        } else {
            showScene("SignIn");
        }
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
        showScene("SignIn");
    }


    @FXML
    void initialize() {
        logoutBtn.setVisible(false);
        queryRidesBtn.setVisible(false);
        createRidesBtn.setVisible(false);
    }

    private void showScene(String scene) {
        switch (scene) {
            case "CreateRide" -> mainWrapper.setCenter(mainGUI.getCreateRidesWin());
            case "QueryRides" -> mainWrapper.setCenter(mainGUI.getQueryRidesWin());
            case "SignIn" -> mainWrapper.setCenter(mainGUI.getSignInWin());
            case "SignUp" -> mainWrapper.setCenter(mainGUI.getSignUpWin());
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

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
