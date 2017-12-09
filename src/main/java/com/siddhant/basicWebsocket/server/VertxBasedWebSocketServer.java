package com.siddhant.basicWebsocket.server;

import com.siddhant.basicWebsocket.handler.WebsocketHandler;
import io.vertx.core.Vertx;

public class VertxBasedWebSocketServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer()
             .websocketHandler(WebsocketHandler.handle())
             .listen(7075);
    }
}