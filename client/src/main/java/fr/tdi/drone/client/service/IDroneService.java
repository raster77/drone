package fr.tdi.drone.client.service;

import fr.tdi.drone.client.model.DroneModel;
import fr.tdi.drone.client.model.ZoneModel;
import fr.tdi.drone.common.messages.Message;
import io.reactivex.Observable;

public interface IDroneService {

    Observable<DroneModel> getDroneModel();

    Observable<ZoneModel> getZoneModel();

    Observable<Message> getMessage();

    String getIdFromDroneModel(DroneModel d);

    void connect();

    void disconnect();

    boolean isConnected();

    // todo Ajouter un proto area w & h

}
