package com.db2020.delivery.config;


import com.db2020.delivery.entity.ChatMessage;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class MessageEncoder implements Encoder.TextStream<JsonObject> {

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public void encode(JsonObject jsonLoad, Writer writer) throws EncodeException, IOException {
        try (JsonWriter jsonWriter = Json.createWriter(writer)) {
            jsonWriter.writeObject(jsonLoad);
        }
    }
    @Override
    public void destroy() {

    }
}