package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.ui.MainGUI;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public interface Controller {
  void setMainApp(MainGUI mainGUI);

  Node getLogoutBtn();

  Node getQueryRidesBtn();

  Node getCreateRidesBtn();

  Node getMyRidesBtn();

  Node getSingUpBtn();

  Node getSingInBtn();

}
