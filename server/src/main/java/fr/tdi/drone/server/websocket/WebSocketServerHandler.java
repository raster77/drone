/**
 * 
 */
package fr.tdi.drone.server.websocket;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import fr.tdi.drone.server.app.ServerApp;

/**
 * @author Thierry
 *
 */
public class WebSocketServerHandler extends WebSocketServer {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServerApp.class);
    private boolean listenning = false;

    public WebSocketServerHandler(int port) {
	super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
	LOGGER.info("Client {} connected on {}", conn.getRemoteSocketAddress().getAddress().getHostName(),
		handshake.getResourceDescriptor());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
	listenning = false;
	LOGGER.info("Client disconnected : {} {}", code, reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
	LOGGER.info("Message from {} : {}", conn.getRemoteSocketAddress().getAddress().getHostName(), message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
	LOGGER.error("Exception: {}", ex.getMessage());
    }

    @Override
    public void onStart() {
	listenning = true;
	LOGGER.info("Ready. Listenning on port {}", this.getPort());
    }

    /**
     * @return the listenning
     */
    public boolean isListenning() {
	return listenning;
    }

}
