package eus.ehu.shareTrip.uicontrollers;


import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.domain.Ride;
import eus.ehu.shareTrip.ui.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
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
    private ImageView profileImage;

    @FXML
    private Button changeImageButton;

    @FXML
    private Label emailLbl;

    @FXML
    private Label nameLbl;

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

    public void showProfileImage() {
        String imagePath = businessLogic.getImagePath((long) businessLogic.getCurrentUser().getId());
        if (imagePath != null) {
            profileImage.setImage(new Image("file:///" + imagePath));
        }
    }

    @FXML
    public void changeImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            businessLogic.setImagePath((long) businessLogic.getCurrentUser().getId(),selectedFile.getAbsolutePath());
            showProfileImage();
        }
    }


    public void showProfileDetails() {
        String name = businessLogic.getCurrentUser().getName();
        String email = businessLogic.getCurrentUser().getEmail();
        nameLbl.setText(name);
        emailLbl.setText(email);
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

