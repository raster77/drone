package fr.tdi.drone.client.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ZoneModel {

    IntegerProperty width = new SimpleIntegerProperty(0);
    IntegerProperty height = new SimpleIntegerProperty(0);

    public ZoneModel(int width, int height) {
	this.width.set(width);
	this.height.set(height);
    }

    public void setZone(int width, int height) {
	this.width.set(width);
	this.height.set(height);
    }

    public int getWidth() {
	return width.get();
    }

    public IntegerProperty widthProperty() {
	return width;
    }

    public void setWidth(int width) {
	this.width.set(width);
    }

    public int getHeight() {
	return height.get();
    }

    public IntegerProperty heightProperty() {
	return height;
    }

    public void setHeight(int height) {
	this.height.set(height);
    }

}
