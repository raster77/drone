package fr.tdi.drone.client.websocket;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;

import ch.qos.logback.classic.Logger;
import fr.tdi.drone.common.messages.Message;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class WebSocketClientHandler extends WebSocketClient {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(WebSocketClient.class);
    private boolean connected = false;
    private final Subject<Message> subjectMessages = BehaviorSubject.create();

    public WebSocketClientHandler(URI serverUri) {
	super(serverUri);
	setReuseAddr(true);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
	LOGGER.info("Connection to {}", getURI());
	connected = true;
    }

    @Override
    public void onMessage(String message) {
	LOGGER.info("Message {}", message);
    }

    @Override
    public void onMessage(ByteBuffer blob) {
	LOGGER.info("Binary message");
	try {
	    Message msg = Message.parseFrom(blob.array());
	    subjectMessages.onNext(msg);
	} catch (InvalidProtocolBufferException e) {
	    LOGGER.error("Error on parsing message: " + e.getMessage());
	}
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
	connected = false;
	LOGGER.info("Disconnected : {} {}", code, reason);
    }

    @Override
    public void onError(Exception ex) {
	LOGGER.error("Exception: {}", ex.getMessage());
    }

    /**
     * @return the connected
     */
    public boolean isConnected() {
	return connected;
    }

    /**
     * Flux de message
     *
     * @return Observable de TestMessage
     */
    public Observable<Message> getSubjectMessages() {
	return subjectMessages;
    }

}
