package eus.ehu.shareTrip.uicontrollers;

import eus.ehu.shareTrip.ui.MainGUI;
import javafx.scene.Node;

public interface Controller {
  void setMainApp(MainGUI mainGUI);

  Node getLogoutBtn();

  Node getQueryRidesBtn();

  Node getCreateRidesBtn();

  Node getMyRidesBtn();
}
