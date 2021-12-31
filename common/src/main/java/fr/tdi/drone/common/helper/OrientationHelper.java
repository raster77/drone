package fr.tdi.drone.common.helper;

import fr.tdi.drone.common.messages.Orientation;

public class OrientationHelper {

    private OrientationHelper() {
	// Empty
    }

    public double getAngleFromOrientation(Orientation o) {
	switch (o) {
	case EAST:
	    return 90;
	case NORTH:
	    return 0;
	case SOUTH:
	    return 180;
	case WEST:
	    return 270;
	default:
	    return 0;
	}
    }

}
