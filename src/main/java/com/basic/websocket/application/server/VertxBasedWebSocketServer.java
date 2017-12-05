package com.basic.websocket.application.server;

import com.basic.websocket.application.handler.WebsocketHandler;
import io.vertx.core.Vertx;

public class VertxBasedWebSocketServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer()
             .websocketHandler(WebsocketHandler.handle())
             .listen(7075);
    }
}