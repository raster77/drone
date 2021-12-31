package fr.tdi.drone.common.helper;

import fr.tdi.drone.common.messages.Orientation;

public class OrientationHelper {

    private OrientationHelper() {
	// Empty
    }

    public double getAngleFromOrientation(Orientation o) {
	switch (o) {
	case EAST:
	    return 270;
	case NORTH:
	    return 0;
	case SOUTH:
	    return 180;
	case WEST:
	    return 90;
	default:
	    return 0;
	}
    }

}
