/**
 * 
 */
package fr.tdi.drone.server.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import fr.tdi.drone.server.command.ServerCommandManager;
import fr.tdi.drone.server.server.DroneServer;

/**
 * @author Thierry
 *
 */
public class ServerApp {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServerApp.class);

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
	DroneServer server = new DroneServer();
	ServerCommandManager cmdManager = new ServerCommandManager(server);
	server.start();
	BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
	LOGGER.info("Ready. Wait command");
	while (true) {
	    String s = sysIn.readLine();
	    if ("exit".equals(s)) {
		server.stop();
		break;
	    } else if ("help".equals(s)) {
		System.out.println(cmdManager.getHelp());
	    } else {
		cmdManager.processInput(s);
	    }
	}
	LOGGER.info("Terminated");
    }

}
