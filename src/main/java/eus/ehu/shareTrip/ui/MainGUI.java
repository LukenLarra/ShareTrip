package eus.ehu.shareTrip.ui;

import eus.ehu.shareTrip.businessLogic.BlFacade;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import eus.ehu.shareTrip.uicontrollers.Controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainGUI {

    private Window mainWin, createRideWin, queryRidesWin, signInSignUpWin, signInWin, signUpWin;

    private BlFacade businessLogic;

    private Stage stage;
    private Scene scene;

    public BlFacade getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BlFacade afi) {
        businessLogic = afi;
    }

    public MainGUI(BlFacade bl) {
        Platform.startup(() -> {
            try {
                setBusinessLogic(bl);
                init(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    class Window {
        Controller c;
        Parent ui;
    }

    private Window load(String fxmlfile) throws IOException {
        Window window = new Window();
        FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(fxmlfile), ResourceBundle.getBundle("Etiquetas", Locale.getDefault()));
        loader.setControllerFactory(controllerClass -> {
            try {
                return controllerClass
                        .getConstructor(BlFacade.class)
                        .newInstance(businessLogic);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        window.ui = loader.load();
        ((Controller) loader.getController()).setMainApp(this);
        window.c = loader.getController();
        return window;
    }

    public void init(Stage stage) throws IOException {

        this.stage = stage;

        mainWin = load("MainGUI.fxml");
        queryRidesWin = load("QueryRides.fxml");
        createRideWin = load("CreateRide.fxml");
        signInWin = load("SignIn.fxml");
        signUpWin = load("SignUp.fxml");


        showMain();
    }

   // public void start(Stage stage) throws IOException {
   //  init(stage);
   //}
    public Parent getQueryRidesWin() {
        return queryRidesWin.ui;
    }

    public Parent getCreateRidesWin() {
        return createRideWin.ui;
    }

    public Parent getSignInWin() {
        return signInWin.ui;
    }

    public Parent getSignUpWin() {
        return signUpWin.ui;
    }


    public void showMain() {
        setupScene(mainWin.ui, "MainTitle", 1000, 600);

    }
    public void showQueryRides() {
        setupScene(queryRidesWin.ui, "QueryRides", 1000, 500);

    }
    public void showCreateRide() {
        setupScene(createRideWin.ui, "CreateRide", 550, 400);

    }


    private void setupScene(Parent ui, String title, int width, int height) {
        if (scene == null) {
            scene = new Scene(ui, width, height);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
        }
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setTitle(ResourceBundle.getBundle("Etiquetas", Locale.getDefault()).getString(title));
        scene.setRoot(ui);
        stage.show();
    }

    public MainGUI getMainGUI(){
        return this;
    }

    //  public static void main(String[] args) {
//    launch();
//  }
}
