package fr.tdi.drone.client.node;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DroneNode extends ImageView {

    private final IntegerProperty posX = new SimpleIntegerProperty(0);
    private final IntegerProperty posY = new SimpleIntegerProperty(0);
    private final DoubleProperty angle = new SimpleDoubleProperty(0);

    public DroneNode() {
	super();
	Image img = new Image("images/plane.png", 32, 32, true, true);
	setImage(img);
	setRotate(0);
	setScaleX(0.8);
	setScaleY(0.8);

	ChangeListener<Number> rotationListener = (observable, oldValue, newValue) -> setRotate(newValue.doubleValue());
	angle.addListener(rotationListener);
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
	setRotate(angle);
    }

}
