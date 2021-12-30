package fr.tdi.drone.client.app;

import fr.tdi.drone.client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DroneClientApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
	Parent root = (Parent) loader.load();
	MainController mainController = (MainController) loader.getController();
	Scene scene = new Scene(root, 800, 600);
	scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

	primaryStage.setOnCloseRequest(e -> mainController.closeClient());

	primaryStage.setScene(scene);
	primaryStage.setTitle("Drone client");
	primaryStage.show();
    }

}
