package fr.tdi.drone.common.helper;

import fr.tdi.drone.common.messages.Movement;
import fr.tdi.drone.common.messages.Orientation;

public class MessageHelper {

    private MessageHelper() {
	// Empty
    }

    public static Orientation getOrientation(String s) {
	switch (s.toUpperCase()) {
	case "E":
	    return Orientation.EAST;
	case "N":
	    return Orientation.NORTH;
	case "S":
	    return Orientation.SOUTH;
	case "W":
	    return Orientation.WEST;
	default:
	    return Orientation.UNRECOGNIZED;
	}
    }

    public static Movement getMovement(String s) {

	switch (s.toUpperCase()) {
	case "D":
	    return Movement.D;
	case "G":
	    return Movement.G;
	case "A":
	    return Movement.A;
	default:
	    return Movement.UNRECOGNIZED;
	}

    }

}
