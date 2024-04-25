package eus.ehu.shareTrip.uicontrollers;


import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class MyRidesDriverController implements Controller {

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    public MyRidesDriverController(BlFacade bl) {
        businessLogic = bl;
    }


    @FXML
    private TableColumn<Ride, String> dateColumn;

    @FXML
    private TableColumn<Ride, String> fromColumn;

    @FXML
    private TableView<Ride> myRidesTable;

    @FXML
    private TableColumn<Ride, Integer> numSeatsColumn;

    @FXML
    private TableColumn<Ride, Integer> rideNumColumn;

    @FXML
    private TableColumn<Ride, String> toColumn;

    @FXML
    void initialize() {
        rideNumColumn.setCellValueFactory(new PropertyValueFactory<>("rideNumber"));
        numSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("numPlaces"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromLocation"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toLocation"));

    }

    @FXML
    public void refreshMyRidesDriver(ActionEvent event) {
        myRidesTable.getItems().clear();
        List<Ride> rides = ((Driver)businessLogic.getCurrentUser()).getRides();
        for (Ride ride : rides) {
            myRidesTable.getItems().add(ride);
        }
    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
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

    @Override
    public void keyboardNav(KeyEvent event) {

    }

}

