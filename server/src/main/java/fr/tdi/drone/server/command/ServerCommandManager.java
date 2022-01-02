package fr.tdi.drone.server.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import fr.tdi.drone.common.messages.Message;
import fr.tdi.drone.server.helper.MessageGeneratorHelper;
import fr.tdi.drone.server.server.DroneServer;

public class ServerCommandManager {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServerCommandManager.class);
    private static final String SEND_CMD = "send command {}";
    private int id = 0;

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

	    if (cmd.isMessage()) {
		buildMessage(cmd);
	    } else {
		if ("read".equalsIgnoreCase(cmd.getVerb())) {
		    readFileAndSendCommands(cmd);
		}
	    }

	} else {
	    LOGGER.info("Unknown command: {}", explodedCommand.get(0));
	}
    }

    private void readFileAndSendCommands(Command cmd) {

	CommandsReader reader = new CommandsReader();
	if (!reader.readFile(cmd.getParameter(0))) {
	    LOGGER.warn("{} does not exists", cmd.getParameter(1));
	} else if (reader.isValid()) {
	    LOGGER.info("Processing commands from file");
	    String c = reader.getLines().get(0);
	    c = c.replace(">", "zone");
	    LOGGER.info(SEND_CMD, c);
	    processInput(c);

	    for (int i = 1; i < reader.getLines().size() - 1; i += 2) {
		id++;
		c = "drone " + id + " " + reader.getLines().get(i);
		LOGGER.info(SEND_CMD, c);
		processInput(c);
		c = "move " + id + " " + reader.getLines().get(i + 1);
		LOGGER.info(SEND_CMD, c);
		processInput(c);
	    }
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

    private void buildMessage(Command cmd) {
	try {
	    String c = cmd.generate();
	    LOGGER.info("Command: {}", c);
	    Optional<Message> optMsg = MessageGeneratorHelper.generateMessageFromCommand(cmd);
	    if (optMsg.isPresent()) {
		server.sendMessage(optMsg.get());
	    }
	} catch (CommandException e) {
	    LOGGER.error(e.getMessage());
	}
    }

    private void initCommands() {
	commands.put(new Command("zone", true, 2),
		"zone: initialize zone. Parameters are width (int) and height (int).");
	commands.put(new Command("drone", true, 4),
		"drone: create a drone. Parameters are id (int), x (int) position, y (int) position and orientation (N, S, E, W).");
	commands.put(new Command("move", true, 2),
		"move: move drone. Parameters are id (int), and sequence of orders (A, D or G).");
	commands.put(new Command("read", false, 1), "read : read a drone commands file.");
    }

}
