package fr.tdi.drone.client.websocket;

import java.net.URI;
import java.net.URISyntaxException;

import fr.tdi.drone.common.messages.Message;
import io.reactivex.Observable;

public class DroneClient {

    private WebSocketClientHandler wsClient;
    private Observable<Message> messages;
    private static final String URL = "ws://localhost:9000/drone";

    public DroneClient() {
	//
    }

    public void connect() throws URISyntaxException {

	URI uri = new URI(URL);
	wsClient = new WebSocketClientHandler(uri);
	wsClient.setReuseAddr(true);

	try {
	    wsClient.connectBlocking();
	} catch (InterruptedException e) {
	    Thread.currentThread().interrupt();
	    wsClient = null;
	}

	messages = wsClient.getSubjectMessages();
    }

    public void disconnect() {
	wsClient.close();
    }

    public boolean isConnected() {
	return wsClient != null && wsClient.isConnected();
    }

    public Observable<Message> getMessages() {
	return messages;
    }

}
