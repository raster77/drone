package fr.tdi.drone.server.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import fr.tdi.drone.common.messages.Message;
import fr.tdi.drone.server.helper.MessageHelper;
import fr.tdi.drone.server.server.DroneServer;

public class ServerCommandManager {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServerCommandManager.class);

    private DroneServer server;
    private Map<Command, String> commands = new HashMap<>();

    public ServerCommandManager(DroneServer server) {
	this.server = server;
	initCommands();
    }

    public void processInput(String command) {
	List<String> explodedCommand = Arrays.asList(command.split("\\ "));
	Optional<Command> optCmd = getCommand(explodedCommand.get(0));
	if (optCmd.isPresent()) {
	    Command cmd = optCmd.get();

	    cmd.clearParameters();
	    explodedCommand.subList(1, explodedCommand.size()).forEach(cmd::addParameter);
	    try {
		String c = cmd.generate();
		LOGGER.info("Command: {}", c);
		Optional<Message> optMsg = MessageHelper.generateMessageFromCommand(cmd);
		if (optMsg.isPresent()) {
		    server.sendMessage(optMsg.get());
		}
	    } catch (CommandException e) {
		LOGGER.error(e.getMessage());
	    }
	} else {
	    LOGGER.info("Unknown command: {}", explodedCommand.get(0));
	}
    }

    public String getHelp() {
	StringBuilder sb = new StringBuilder("\nCommands :\n");
	commands.forEach((k, v) -> sb.append("\t" + v + "\n"));
	return sb.toString();
    }

    private Optional<Command> getCommand(String verb) {
	return commands.keySet().stream()
		.filter(c -> !c.getVerb().trim().isEmpty() && c.getVerb().trim().equalsIgnoreCase(verb)).findFirst();
    }

    private void initCommands() {
	commands.put(new Command("zone", 2), "zone: initialize zone. Parameters are width (int) and height (int).");
	commands.put(new Command("drone", 3),
		"drone: create a drone. Parameters are id (int), x (int) position and y (int) position.");
	commands.put(new Command("move", 2),
		"move: move drone. Parameters are id (int), and sequence of orders (A, D or G).");
	commands.put(new Command("clear", 0), "clear : delete all drones.");
    }

}
