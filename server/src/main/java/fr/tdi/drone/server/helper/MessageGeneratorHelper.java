package fr.tdi.drone.server.helper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.tdi.drone.common.helper.MessageHelper;
import fr.tdi.drone.common.messages.Drone;
import fr.tdi.drone.common.messages.DroneMove;
import fr.tdi.drone.common.messages.Message;
import fr.tdi.drone.common.messages.MessageType;
import fr.tdi.drone.common.messages.Movement;
import fr.tdi.drone.common.messages.Position;
import fr.tdi.drone.common.messages.Zone;
import fr.tdi.drone.server.command.Command;
import fr.tdi.drone.server.command.CommandException;

public class MessageGeneratorHelper {

    private MessageGeneratorHelper() {
	// Empty
    }

    public static Optional<Message> generateMessageFromCommand(Command cmd) throws CommandException {
	Message msg = null;
	try {
	    switch (cmd.getVerb()) {
	    case "zone":
		msg = getZoneMessage(cmd);
		break;
	    case "drone":
		msg = getDroneMessage(cmd);
		break;
	    case "move":
		msg = getMoveMessage(cmd);
		break;
	    default:
		msg = null;
	    }

	    return Optional.of(msg);
	} catch (Exception e) {
	    throw new CommandException("Invalid command.");
	}
    }

    private static Message getZoneMessage(Command cmd) {
	Zone zone = Zone.newBuilder().setWidth(Integer.parseInt(cmd.getParameter(0)))
		.setHeight(Integer.parseInt(cmd.getParameter(1))).build();
	return Message.newBuilder().setMessageType(MessageType.ZONE).setDatas(zone.toByteString()).build();
    }

    private static Message getDroneMessage(Command cmd) {
	Drone drone = Drone.newBuilder().setId(Integer.parseInt(cmd.getParameter(0)))
		.setPosition(Position.newBuilder().setX(Integer.parseInt(cmd.getParameter(1)))
			.setY(Integer.parseInt(cmd.getParameter(2)))
			.setOrientation(MessageHelper.getOrientation(cmd.getParameter(3))).build())
		.build();
	return Message.newBuilder().setMessageType(MessageType.DRONE).setDatas(drone.toByteString()).build();
    }

    private static Message getMoveMessage(Command cmd) {
	DroneMove move = DroneMove.newBuilder().setId(Integer.parseInt(cmd.getParameter(0)))
		.addAllMoves(getMovement(cmd.getParameter(1))).build();
	return Message.newBuilder().setMessageType(MessageType.MOVE).setDatas(move.toByteString()).build();
    }

    private static List<Movement> getMovement(String s) {

	return s.chars().mapToObj(c -> (char) c).map(c -> MessageHelper.getMovement(String.valueOf(c)))
		.filter(m -> m != Movement.UNRECOGNIZED).collect(Collectors.toList());
    }

}
