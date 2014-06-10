package sample;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/wsexample")
public class WebSocketEndpoint {
    private static Set<Session> sessionSet
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void open(Session session) {
        sessionSet.add(session);
    }

    @OnClose
    public void close(Session session) {
        sessionSet.remove(session);
    }

    @OnMessage
    public void onMessage(String message) {
        for (Session session : sessionSet) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
