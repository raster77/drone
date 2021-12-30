package fr.tdi.drone.client.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.tdi.drone.client.model.DroneModel;
import fr.tdi.drone.client.model.ZoneModel;
import fr.tdi.drone.client.node.DroneNode;
import fr.tdi.drone.client.service.IDroneService;
import fr.tdi.drone.client.service.IZoneService;
import fr.tdi.drone.client.service.InjectionModule;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainController implements Initializable {

    private static final String CONNECT = " Connect ";
    private static final String DISCONNECT = "Disonnect";
    private static final String SPACER = "     ";
    private ZoneModel zone = new ZoneModel(10, 10);

    // Association drone modèle/node
    private final HashMap<Integer, Pair<DroneModel, DroneNode>> mapDrone = new HashMap<>();

    // Services
    private IZoneService zoneService;
    private IDroneService droneService;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnConnect;

    @FXML
    private TextArea logArea;

    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	// Injection des services
	Injector injector = Guice.createInjector(new InjectionModule());
	zoneService = injector.getInstance(IZoneService.class);
	droneService = injector.getInstance(IDroneService.class);

	btnConnect.setText(CONNECT);
	setGrid();
    }

    public void closeClient() {
	droneService.disconnect();
    }

    public void btnQuitClick() {
	closeClient();
	Stage stage = (Stage) grid.getScene().getWindow();
	stage.close();
    }

    public void btnClearLog() {
	logArea.clear();
    }

    public void onConnectDisconnect() {

	if (droneService.isConnected()) {
	    droneService.disconnect();
	} else {
	    droneService.connect();
	    droneService.getDroneModel().buffer(1, TimeUnit.SECONDS).filter(l -> !l.isEmpty())
		    .concatMap(Observable::fromIterable).concatMap(d -> Observable.just(d).delay(1, TimeUnit.SECONDS))
		    .observeOn(JavaFxScheduler.platform()).subscribe(this::processDroneModels);

	    droneService.getZoneModel().observeOn(JavaFxScheduler.platform()).subscribe(zoneModel -> {
		writeLog("New zone def : " + zoneModel.getWidth() + " " + zoneModel.getHeight());
		zone.setWidth(zoneModel.getWidth());
		zone.setHeight(zoneModel.getHeight());
		setGrid();
	    });
	}
	btnConnect.setText(droneService.isConnected() ? DISCONNECT : CONNECT);
    }

    private void writeLog(String s) {
	logArea.appendText(s + "\n");
    }

    private void setGrid() {
	grid.getChildren().clear();
	for (int h = 0; h < zone.getHeight(); ++h) {
	    ColumnConstraints c = new ColumnConstraints();
	    c.setHgrow(Priority.SOMETIMES);
	    c.setHalignment(HPos.CENTER);
//	    grid.getColumnConstraints().add(c);
	    for (int w = 0; w < zone.getWidth(); ++w) {
		RowConstraints r = new RowConstraints();
		r.setVgrow(Priority.SOMETIMES);
		r.setValignment(VPos.CENTER);
		final String id = w + "." + h;
		StackPane pane = new StackPane();
		pane.setId(id);
//		grid.getRowConstraints().add(r);
		Label lbl = new Label();
		lbl.setText(SPACER + "\n" + SPACER + id + SPACER + "\n" + SPACER);
		pane.getChildren().add(lbl);
		grid.add(pane, h, w);
	    }
	}
    }

    private void processDroneModels(DroneModel droneModels) {
	writeLog("Traitement du drone " + droneModels.getId());
	if (!mapDrone.containsKey(droneModels.getId())) {
	    mapDrone.put(droneModels.getId(), new Pair<>(droneModels, createDroneNode(droneModels)));
	}
	DroneNode node = mapDrone.get(droneModels.getId()).getValue();
	node.setPosX(droneModels.getPosX());
	writeLog("node X = " + node.getPosX());
	node.setPosY(droneModels.getPosY());
	node.setAngle(droneModels.getAngle());
	updateDrone(droneModels);

	// todo dans droneService, verifier si mouvement valide et
    }

    private DroneNode createDroneNode(DroneModel drone) {
	// Listener sur les propriétés x, y, et angle afin de mettre à jour le droneNode
	DroneNode node = new DroneNode();
	ChangeListener<Number> droneChangeListener = (observable, oldValue, newValue) -> updateDrone(drone);
	node.angleProperty().addListener(droneChangeListener);
	node.posYProperty().addListener(droneChangeListener);
	node.posXProperty().addListener(droneChangeListener);
	return node;
    }

    private void updateDrone(DroneModel model) {
	writeLog("Update drone " + model.getId());
	boolean test = zoneService.isMoveValid(model, zone);
	DroneNode droneNode = mapDrone.get(model.getId()).getValue();
	writeLog("Drone update");
	final String id = droneService.getIdFromDroneModel(model);
	Optional<Node> nodeOpt = grid.getChildren().stream().filter(n -> id.equals(n.getId())).findFirst();
	if (nodeOpt.isPresent()) {
	    for (Node n : grid.getChildren()) {
		StackPane s = (StackPane) n;
		s.getChildren().remove(droneNode);
	    }

	    StackPane pane = (StackPane) nodeOpt.get();
	    pane.getChildren().add(droneNode);

	}
    }

}
