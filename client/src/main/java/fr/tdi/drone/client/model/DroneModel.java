package fr.tdi.drone.client.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Modèle représentant un drone
 */
public class DroneModel {

    private int id = 0;
    private final IntegerProperty posX = new SimpleIntegerProperty(0);
    private final IntegerProperty posY = new SimpleIntegerProperty(0);
    private final DoubleProperty angle = new SimpleDoubleProperty(0);

    public DroneModel(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getPosX() {
	return posX.get();
    }

    public IntegerProperty posXProperty() {
	return posX;
    }

    public void setPosX(int posX) {
	this.posX.set(posX);
    }

    public int getPosY() {
	return posY.get();
    }

    public IntegerProperty posYProperty() {
	return posY;
    }

    public void setPosY(int posY) {
	this.posY.set(posY);
    }

    public double getAngle() {
	return angle.get();
    }

    public DoubleProperty angleProperty() {
	return angle;
    }

    public void setAngle(double angle) {
	this.angle.set(angle);
    }
}
