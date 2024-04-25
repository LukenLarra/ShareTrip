package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.RideRequest;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyRidesTravelerController implements Controller{

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    public MyRidesTravelerController(BlFacade bl) {
        businessLogic = bl;
    }


    @FXML
    private Button deleteRequestButton;

    @FXML
    private TableView<RideRequest> myRidesTable;

    @FXML
    private TableColumn<RideRequest, Integer> requestIdColumn;

    @FXML
    private TableColumn<RideRequest, Integer> numSeatsColumn;

    @FXML
    private TableColumn<RideRequest, String> dateColumn;

    @FXML
    private TableColumn<RideRequest, String> requestCodeColumn;

    @FXML
    private TableColumn<RideRequest, String> statusColumn;

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

    @FXML
    void initialize(){
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        numSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("numSeats"));
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RideRequest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RideRequest, String> cellData) {
                LocalDate date = cellData.getValue().getDate();
                String formattedDate = (date != null) ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
                return new SimpleStringProperty(formattedDate);
            }
        });
        requestCodeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    public void deleteRequest(ActionEvent event){
        RideRequest rideRequest = myRidesTable.getSelectionModel().getSelectedItem();
        if (rideRequest != null && !rideRequest.getStatus().equals("PENDING")) {
            businessLogic.deleteRideRequest(rideRequest.getReservationCode());
            myRidesTable.getItems().remove(rideRequest);
        }

    }

    @Override @FXML
    public void keyboardNav(KeyEvent event) {

    }

    @FXML
    public void refreshMyRidesTraveler(ActionEvent event) {
        myRidesTable.getItems().clear();
        List<RideRequest> rideRequests = businessLogic.getRideRequestsForTraveler(businessLogic.getCurrentUser().getId());
        for (RideRequest rideRequest : rideRequests) {
                myRidesTable.getItems().add(rideRequest);
        }
    }
}



