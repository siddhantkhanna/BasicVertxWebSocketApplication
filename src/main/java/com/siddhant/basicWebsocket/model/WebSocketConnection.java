package com.siddhant.basicWebsocket.model;

import io.vertx.core.http.ServerWebSocket;

public class WebSocketConnection {
    private ServerWebSocket serverWebSocket;
    private Long connectionId;
    private String name;

    public ServerWebSocket getServerWebSocket() {
        return serverWebSocket;
    }

    public void setServerWebSocket(ServerWebSocket serverWebSocket) {
        this.serverWebSocket = serverWebSocket;
    }

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public WebSocketConnection() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebSocketConnection(ServerWebSocket serverWebSocket, String name, Long connectionId) {
        super();
        this.serverWebSocket = serverWebSocket;
        this.name = name;
        this.connectionId = connectionId;
    }
}
