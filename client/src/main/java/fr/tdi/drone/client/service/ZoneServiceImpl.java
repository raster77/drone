package fr.tdi.drone.client.service;

import com.google.inject.Singleton;

import fr.tdi.drone.client.model.DroneModel;
import fr.tdi.drone.client.model.ZoneModel;

@Singleton
public class ZoneServiceImpl implements IZoneService {

    public boolean isMoveValid(DroneModel drone, ZoneModel zone) {
	return drone.getPosX() >= 0 && drone.getPosY() >= 0 && drone.getPosX() <= zone.getWidth()
		&& drone.getPosY() <= zone.getHeight();
    }

}
