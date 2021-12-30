package fr.tdi.drone.server.server;

import fr.tdi.drone.common.messages.Message;
import fr.tdi.drone.server.websocket.WebSocketServerHandler;

public class DroneServer {

    private int port = 9000;
    private WebSocketServerHandler wsHandler;

    public DroneServer() {
	// Empty constructor
    }

    public DroneServer(int port) {
	this.port = port;
    }

    public void start() {
	if (wsHandler == null) {
	    wsHandler = new WebSocketServerHandler(port);
	}

	wsHandler.start();
    }

    public void stop() throws InterruptedException {
	if (wsHandler != null && wsHandler.isListenning()) {
	    wsHandler.stop(1000);
	}
    }

    public void sendMessage(String msg) {
	wsHandler.broadcast(msg);
    }

    public void sendMessage(Message msg) {
	wsHandler.broadcast(msg.toByteArray());
    }

}
