package eus.ehu.shareTrip.uicontrollers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.domain.RideRequest;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

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
        rideNumColumn.setCellValueFactory(new PropertyValueFactory<>("rideNum"));
        numSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("numSeats"));
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ride, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Ride, String> cellData) {
                LocalDate date = cellData.getValue().getDate();
                String formattedDate = (date != null) ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
                return new SimpleStringProperty(formattedDate);
            }
        });
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromLocation"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toLocation"));

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

