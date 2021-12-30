package fr.tdi.drone.client.service;

import fr.tdi.drone.client.model.DroneModel;
import fr.tdi.drone.client.model.ZoneModel;

public interface IZoneService {

    boolean isMoveValid(DroneModel drone, ZoneModel zone);

}
