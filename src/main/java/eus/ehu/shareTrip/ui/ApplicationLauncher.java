package eus.ehu.shareTrip.ui;

import eus.ehu.shareTrip.configuration.Config;
import eus.ehu.shareTrip.domain.Driver;
import eus.ehu.shareTrip.businessLogic.BlFacade;
import eus.ehu.shareTrip.businessLogic.BlFacadeImplementation;

import java.util.Locale;

public class ApplicationLauncher {

  public static void main(String[] args) {

    Config config = Config.getInstance();

    Locale.setDefault(new Locale(config.getLocale()));
    System.out.println("Locale: " + Locale.getDefault());

    BlFacade businessLogic;

    try {

      if (config.isBusinessLogicLocal()) {
        businessLogic = new BlFacadeImplementation();


      Driver driver=new Driver("driver3@gmail.com","Test Driver", "1111", "src/main/resources/images/defaultProfile.png");
      businessLogic.setCurrentUser(driver);


      new MainGUI(businessLogic);

      businessLogic.setCurrentUser(null);
      }
    }
    catch (Exception e) {
      System.err.println("Error in ApplicationLauncher: " + e);
    }

  }


}
