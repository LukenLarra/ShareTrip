package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.RideRequest;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyRidesController implements Controller{

    private MainGUI mainGUI;

    private BlFacade businessLogic;

    public MyRidesController(BlFacade bl) {
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

    @FXML
    public void deleteRequest(ActionEvent event){

    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

}



