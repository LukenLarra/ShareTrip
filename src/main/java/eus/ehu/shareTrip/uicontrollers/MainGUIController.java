package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUIController implements Controller{

    @FXML
    private BorderPane mainWrapper;

    @FXML
    private Label selectOptionLbl;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserType;

    @FXML
    private Button queryRidesBtn;

    @FXML
    private Button createRideBtn;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        //mainGUI.showQueryRides();
        showScene("QueryRides");
    }

    @FXML
    void createRide(ActionEvent event) {
        //mainGUI.showCreateRide();
        showScene("CreateRide");
    }


    @FXML
    void initialize() {
        //lblUserType.setText(businessLogic.getCurrentDriver().getClass().getSimpleName() + ":");
        //lblUserName.setText(businessLogic.getCurrentDriver().getName());
    }

    private void showScene(String scene) {
        switch (scene) {
            case "CreateRide" -> mainWrapper.setCenter(mainGUI.getCreateRidesWin());
            case "QueryRides" -> mainWrapper.setCenter(mainGUI.getQueryRidesWin());
        }
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
