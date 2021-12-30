package fr.tdi.drone.client.service;

import com.google.inject.AbstractModule;

public class InjectionModule extends AbstractModule {

    @Override
    protected void configure() {
	bind(IZoneService.class).to(ZoneServiceImpl.class);
	bind(IDroneService.class).to(DroneServiceImpl.class);
    }
}
