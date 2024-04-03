package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.businessLogic.BlFacadeImplementation;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class ManageRequestsController implements Controller {

        @FXML
        private ListView<Ride> rideRequestList;

        @FXML
        private Button acceptButton;

        @FXML
        private Button declineButton;

        @FXML
        private Label message;
        private MainGUI mainGUI;

        private BlFacade businessLogic;



    @FXML
        void initialize() {

        }

    @FXML
    void acceptRideRequest(ActionEvent event) {

    }

    @FXML
    void declineRideRequest(ActionEvent event) {

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {

    }
}
