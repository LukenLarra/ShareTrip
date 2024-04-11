package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.domain.RideRequest;
import eus.ehu.shareTrip.domain.User;
import eus.ehu.shareTrip.exceptions.RideAlreadyExistException;
import eus.ehu.shareTrip.exceptions.RideMustBeLaterThanTodayException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.util.Callback;
import eus.ehu.shareTrip.ui.MainGUI;
import eus.ehu.shareTrip.utils.Dates;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateRideController implements Controller{

    public CreateRideController(BlFacade bl) {
        this.businessLogic = bl;
    }

    private BlFacade businessLogic;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datePicker;

    private MainGUI mainGUI;

    @FXML
    private Button refreshButton;

    @FXML
    private Label lblErrorMessage;

    @FXML
    private Label lblErrorMinBet;

    @FXML
    private TableView<RideRequest> rideRequestTable;

    @FXML
    private TableColumn<RideRequest, Integer> requestIdColumn;

    @FXML
    private TableColumn<RideRequest, Integer> numSeatsColumn;

    @FXML
    private TableColumn<RideRequest, String> dateColumn;

    @FXML
    private TableColumn<RideRequest, String> requestCodeColumn;

    @FXML
    private Button btnCreateRide;

    @FXML
    private TextField txtArrivalCity;

    @FXML
    private TextField txtDepartCity;

    @FXML
    private TextField txtNumberOfSeats;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button acceptRequestBtn;

    @FXML
    private Button rejectRequestBtn;

    @FXML
    private TextField txtRequestCode;

    @FXML
    private Label messageRequest;

    @FXML
    void closeClick(ActionEvent event) {
        clearErrorLabels();
        mainGUI.showMain();
    }

    private void clearErrorLabels() {
        lblErrorMessage.setText("");
        lblErrorMinBet.setText("");
        lblErrorMinBet.getStyleClass().clear();
        lblErrorMessage.getStyleClass().clear();
    }


    private String field_Errors() {

        try {
            if ((txtDepartCity.getText().length() == 0) || (txtArrivalCity.getText().length() == 0) || (txtNumberOfSeats.getText().length() == 0) || (txtPrice.getText().length() == 0))
                return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorQuery");
            else {

                // trigger an exception if the introduced string is not a number
                int inputSeats = Integer.parseInt(txtNumberOfSeats.getText());

                if (inputSeats <= 0) {
                    return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.SeatsMustBeGreaterThan0");
                } else {
                    float price = Float.parseFloat(txtPrice.getText());
                    if (price <= 0)
                        return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.PriceMustBeGreaterThan0");

                    else
                        return null;

                }
            }
        } catch (NumberFormatException e1) {

            return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorNumber");
        } catch (Exception e1) {

            e1.printStackTrace();
            return null;

        }
    }

    void displayMessage(String message, String label){
        lblErrorMessage.getStyleClass().clear();
        lblErrorMessage.getStyleClass().setAll("lbl", "lbl-"+label);
        lblErrorMessage.setText(message);
    }

    @FXML
    void acceptRequest(ActionEvent event) {
        String requestCode = txtRequestCode.getText();
        RideRequest selectedRequest = rideRequestTable.getSelectionModel().getSelectedItem();
        if (requestCode.isEmpty() && selectedRequest == null) {
            messageRequest.setText("Please enter a request code or select a request from the table");
            messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
        }else if (!requestCode.isEmpty() && selectedRequest == null){
            try {
                businessLogic.acceptRequest(requestCode);
                refreshRideRequests(event);
                messageRequest.setText("Request accepted successfully");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-background-radius: 5px; -fx-text-radius: 5px;");
                txtRequestCode.clear();
            } catch (Exception e) {
                messageRequest.setText("No request was found with the given code");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
            }
        }else if (requestCode.isEmpty()){
            try {
                businessLogic.acceptRequest(selectedRequest.getReservationCode());
                refreshRideRequests(event);
                selectedRequest.setStatus("Accepted");
                messageRequest.setText("Request accepted successfully");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-background-radius: 5px; -fx-text-radius: 5px;");
                txtRequestCode.clear();
            } catch (Exception e) {
                messageRequest.setText("Error accepting the selected request");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
            }
        }else{
            messageRequest.setText("Please enter a request code or select a request from the table, not both");
            messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
        }
    }

    @FXML
    void rejectRequest(ActionEvent event) {
        String requestCode = txtRequestCode.getText();
        RideRequest selectedRequest = rideRequestTable.getSelectionModel().getSelectedItem();
        if (requestCode.isEmpty() && selectedRequest == null) {
            messageRequest.setText("Please enter a request code or select a request from the table");
            messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
        } else if (!requestCode.isEmpty() && rideRequestTable.getSelectionModel().getSelectedItem() == null){
            try {
                businessLogic.rejectRequest(requestCode);
                refreshRideRequests(event);
                selectedRequest.setStatus("Rejected");
                messageRequest.setText("Request rejected successfully");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-background-radius: 5px; -fx-text-radius: 5px;");
                txtRequestCode.clear();
            } catch (Exception e) {
                messageRequest.setText("No request was found with the given code");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
            }
        }else if (selectedRequest != null && requestCode.isEmpty()){
            try {
                businessLogic.rejectRequest(selectedRequest.getReservationCode());
                refreshRideRequests(event);
                selectedRequest.setStatus("Rejected");
                messageRequest.setText("Request rejected successfully");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-background-radius: 5px; -fx-text-radius: 5px;");
                txtRequestCode.clear();
            } catch (Exception e) {
                messageRequest.setText("Error rejecting the selected request");
                messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
            }
        }else{
            messageRequest.setText("Enter a request code or select a request from the table, not both");
            messageRequest.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-background-radius: 5px; -fx-text-radius: 5px;");
        }
    }

    @FXML
    public void refreshRideRequests(ActionEvent event) {
        rideRequestTable.getItems().clear();
        List<RideRequest> rideRequests = businessLogic.getRideRequestsForDriver(businessLogic.getCurrentUser().getId());
        rideRequestTable.getItems().addAll(rideRequests);
    }

    @FXML
    void createRideClick(ActionEvent e) {

        clearErrorLabels();

        //  Event event = comboEvents.getSelectionModel().getSelectedItem();
        String errors = field_Errors();

        if (errors != null) {
            // businessLogic.createQuestion(event, inputQuestion, inputPrice);
            displayMessage(errors, "danger");

        } else {
            try {

                int inputSeats = Integer.parseInt(txtNumberOfSeats.getText());
                float price = Float.parseFloat(txtPrice.getText());
                User user = businessLogic.getCurrentUser();
                Ride r = businessLogic.createRide(txtDepartCity.getText(), txtArrivalCity.getText(), Dates.convertToDate(datePicker.getValue()), inputSeats, price, user.getEmail());
                displayMessage(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideCreated"), "success");


            } catch (RideMustBeLaterThanTodayException e1) {
                displayMessage(e1.getMessage(), "danger");
            } catch (RideAlreadyExistException e1) {
                displayMessage(e1.getMessage(), "danger");
            }
        }

/*
    if (lblErrorMinBet.getText().length() > 0 && showErrors) {
      lblErrorMinBet.getStyleClass().setAll("lbl", "lbl-danger");
    }
    if (lblErrorQuestion.getText().length() > 0 && showErrors) {
      lblErrorQuestion.getStyleClass().setAll("lbl", "lbl-danger");
    }
 */
    }


    private List<LocalDate> holidays = new ArrayList<>();

  /*private void setEventsPrePost(int year, int month) {
    LocalDate date = LocalDate.of(year, month, 1);
    setEvents(date.getYear(), date.getMonth().getValue());
    setEvents(date.plusMonths(1).getYear(), date.plusMonths(1).getMonth().getValue());
    setEvents(date.plusMonths(-1).getYear(), date.plusMonths(-1).getMonth().getValue());
  }*/

 /* private void setEvents(int year, int month) {

    Date date = Dates.toDate(year, month);

    for (Date day : businessLogic.getEventsMonth(date)) {
      holidays.add(Dates.convertToLocalDateViaInstant(day));
    }
  }*/

    @FXML
    void initialize() {


        // btnCreateRide.setDisable(true);

        // only show the text of the event in the combobox (without the id)
/*
    Callback<ListView<Event>, ListCell<Event>> factory = lv -> new ListCell<>() {
      @Override
      protected void updateItem(Event item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getDescription());
      }
    };


     comboEvents.setCellFactory(factory);
    comboEvents.setButtonCell(factory.call(null));

 */


        // setEventsPrePost(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());


        // get a reference to datepicker inner content
        // attach a listener to the  << and >> buttons
        // mark events for the (prev, current, next) month and year shown
        datePicker.setOnMouseClicked(e -> {
            DatePickerSkin skin = (DatePickerSkin) datePicker.getSkin();
            skin.getPopupContent().lookupAll(".button").forEach(node -> {
                node.setOnMouseClicked(event -> {
                    List<Node> labels = skin.getPopupContent().lookupAll(".label").stream().toList();
                    String month = ((Label) (labels.get(0))).getText();
                    String year = ((Label) (labels.get(1))).getText();
                    YearMonth ym = Dates.getYearMonth(month + " " + year);
                    // setEventsPrePost(ym.getYear(), ym.getMonthValue());
                });
            });


        });

        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            if (holidays.contains(item)) {
                                this.setStyle("-fx-background-color: pink");
                            }
                        }
                    }
                };
            }
        });

        // when a date is selected...
        datePicker.setOnAction(actionEvent -> {
     /* comboEvents.getItems().clear();

      oListEvents = FXCollections.observableArrayList(new ArrayList<>());
      oListEvents.setAll(businessLogic.getEvents(Dates.convertToDate(datePicker.getValue())));

      comboEvents.setItems(oListEvents);

      if (comboEvents.getItems().size() == 0)
        btnCreateRide.setDisable(true);
      else {
         btnCreateRide.setDisable(false);
        // select first option
        comboEvents.getSelectionModel().select(0);
      }
*/
        });


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



    }

    @Override
    public void setMainApp(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
