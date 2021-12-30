package fr.tdi.drone.server.helper;

import java.util.Optional;

import fr.tdi.drone.common.messages.Drone;
import fr.tdi.drone.common.messages.Message;
import fr.tdi.drone.common.messages.MessageType;
import fr.tdi.drone.common.messages.Orientation;
import fr.tdi.drone.common.messages.Position;
import fr.tdi.drone.common.messages.Zone;
import fr.tdi.drone.server.command.Command;

public class MessageHelper {

    public static Optional<Message> generateMessageFromCommand(Command cmd) {
	Message msg = null;
	switch (cmd.getVerb()) {
	case "zone":
	    msg = getZoneMessage(cmd);
	    break;
	case "drone":
	    msg = getDroneMessage(cmd);

	}

	return Optional.of(msg);
    }

    private static Message getZoneMessage(Command cmd) {
	Zone zone = Zone.newBuilder().setWidth(Integer.parseInt(cmd.getParameter(0)))
		.setHeight(Integer.parseInt(cmd.getParameter(1))).build();
	return Message.newBuilder().setMessageType(MessageType.ZONE).setDatas(zone.toByteString()).build();
    }

    private static Message getDroneMessage(Command cmd) {
	Drone drone = Drone.newBuilder().setId(Integer.parseInt(cmd.getParameter(0)))
		.setPosition(Position.newBuilder().setX(Integer.parseInt(cmd.getParameter(1)))
			.setY(Integer.parseInt(cmd.getParameter(2))).setOrientation(Orientation.EAST).build())
		.build();
	return Message.newBuilder().setMessageType(MessageType.DRONE).setDatas(drone.toByteString()).build();
    }

}
