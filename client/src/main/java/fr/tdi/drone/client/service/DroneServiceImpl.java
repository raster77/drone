package fr.tdi.drone.client.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;
import com.google.protobuf.InvalidProtocolBufferException;

import ch.qos.logback.classic.Logger;
import fr.tdi.drone.client.model.DroneModel;
import fr.tdi.drone.client.model.ZoneModel;
import fr.tdi.drone.client.websocket.DroneClient;
import fr.tdi.drone.common.messages.Drone;
import fr.tdi.drone.common.messages.Message;
import fr.tdi.drone.common.messages.Orientation;
import fr.tdi.drone.common.messages.Zone;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

@Singleton
public class DroneServiceImpl implements IDroneService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DroneServiceImpl.class);
    private final List<DroneModel> droneModels = new ArrayList<>();
    private final Subject<DroneModel> droneModelSubject = BehaviorSubject.create();

    private final Subject<ZoneModel> zoneModel = BehaviorSubject.create();
    private final DroneClient client = new DroneClient();

    @Override
    public Observable<DroneModel> getDroneModel() {
	return droneModelSubject;
    }

    @Override
    public Observable<ZoneModel> getZoneModel() {
	return zoneModel;
    }

    @Override
    public Observable<Message> getMessage() {
	return null;
    }

    @Override
    public String getIdFromDroneModel(DroneModel d) {
	return d.getPosX() + "." + d.getPosY();
    }

    @Override
    public void connect() {
	try {
	    client.connect();
	    client.getMessages().subscribe(this::processMessage, e -> {
		e.printStackTrace();
		LOGGER.error(e.getMessage());
	    });
	} catch (URISyntaxException e) {
	    LOGGER.error(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Override
    public void disconnect() {
	client.disconnect();
    }

    @Override
    public boolean isConnected() {
	return client.isConnected();
    }

    private void processMessage(Message msg) {
	LOGGER.info("processMessage: {}", msg);
	switch (msg.getMessageType()) {
	case ZONE:
	    processZone(msg);
	    break;
	case DRONE:
	    processDrone(msg);
	    break;
	default:
	    processMove(msg);
	    break;
	}

	// todo faire les contrôles sur les positions et déplacements
	/*
	 * try { Drone drone = Drone.parseFrom(msg.getDatas()); DroneModel refDrone =
	 * droneModels.stream().filter(d -> d.getId() ==
	 * drone.getId()).findFirst().orElse(null);
	 * droneModels.add(mapDroneToModel(drone, refDrone));
	 * droneModelSubject.onNext(droneModels.get(droneModels.size() - 1)); } catch
	 * (InvalidProtocolBufferException e) { e.printStackTrace(); }
	 */
    }

    private void processZone(Message msg) {
	try {
	    Zone z = Zone.parseFrom(msg.getDatas());
	    zoneModel.onNext(new ZoneModel(z.getWidth(), z.getHeight()));
	} catch (InvalidProtocolBufferException e) {
	    e.printStackTrace();
	}
    }

    private void processDrone(Message msg) {
	try {
	    Drone drone = Drone.parseFrom(msg.getDatas());
	    DroneModel refDrone = droneModels.stream().filter(d -> d.getId() == drone.getId()).findFirst().orElse(null);
	    droneModels.add(mapDroneToModel(drone, refDrone));
	    DroneModel currentDrone = refDrone == null ? droneModels.get(droneModels.size() - 1) : refDrone;
	    List<DroneModel> drones = new ArrayList<>();
	    drones.add(currentDrone);
	    drones.forEach(droneModelSubject::onNext);
	    // droneModelSubject.onNext(refDrone == null ?
	    // droneModels.get(droneModels.size() - 1) : refDrone);
	} catch (InvalidProtocolBufferException e) {
	    e.printStackTrace();
	}
    }

    private void processMove(Message msg) {
	try {
	    Drone drone = Drone.parseFrom(msg.getDatas());
	    DroneModel refDrone = droneModels.stream().filter(d -> d.getId() == drone.getId()).findFirst().orElse(null);
	    droneModels.add(mapDroneToModel(drone, refDrone));
	    droneModelSubject.onNext(droneModels.get(droneModels.size() - 1));
	} catch (InvalidProtocolBufferException e) {
	    e.printStackTrace();
	}
    }

    private DroneModel mapDroneToModel(Drone d, DroneModel m) {
	if (m == null) {
	    m = new DroneModel(d.getId());
	}
	m.setPosX(d.getPosition().getX());
	m.setPosY(d.getPosition().getY());
	m.setAngle(getValueFromOrientation(d.getPosition().getOrientation()));
	return m;
    }

    private double getValueFromOrientation(Orientation o) {
	switch (o) {
	case NORTH:
	    return 0;
	case SOUTH:
	    return 180;
	case EAST:
	    return 270;
	default:
	    return 90;
	}
    }
}
