package com.basic.websocket.application.handler;

import com.basic.websocket.application.model.WebSocketConnection;
import io.vertx.core.Handler;
import io.vertx.core.http.ServerWebSocket;

import java.util.ArrayList;
import java.util.List;

public class WebsocketHandler {
    private static List<WebSocketConnection> connections = new ArrayList<>();

    // User handling has not been done
    // because that depends on case to case.
    // I would suggest running a PING-PONG system
    // with all users with some timeout

    public static Handler<ServerWebSocket> handle() {
        return _serverWebSocket -> {
            String name = _serverWebSocket.query().split("=")[1];
            if (name == null || name.trim().equals("")) {
                _serverWebSocket.writeFinalTextFrame("You are rejected as we didn't receive your name.");
                _serverWebSocket.reject();
                return;
            }

            connections.add(new WebSocketConnection(_serverWebSocket, name, (long) connections.size()));
            System.out.println("new web socket request arrived");

            _serverWebSocket.handler(buffer -> {
                String message = buffer.toString();
                System.out.println("Message received on websocket " + message);

                /*
                    * This is my example of handling messages.
                    * This is a simple case for a group chat.
                    * The message goes to all connected users
                    * except for the one who sent the message
                */

                Long id = null;

                for (WebSocketConnection connection : connections) {
                    if (connection.getServerWebSocket().equals(_serverWebSocket)) {
                        id = connection.getConnectionId();
                    }
                }

                broadcastMessage(message, id);
            });
        };
    }

    private static void broadcastMessage(String message, Long id) {
        connections.forEach(connection -> {
            if (connection.getConnectionId().equals(id)) return;

            connection.getServerWebSocket().writeFinalTextFrame("{\"name\": \"" + connection.getName() + "\", \"message\": \"" + message + "\"}");
        });
    }
}