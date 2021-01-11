package com.db2020.delivery.config;

import com.db2020.delivery.entity.ChatMessage;
import org.springframework.context.annotation.Configuration;


import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Reader;

public class MessageDecoder implements Decoder.TextStream<JsonObject>{

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public void destroy() {}

    @Override
    public JsonObject decode(Reader reader) throws DecodeException, IOException {
        try (JsonReader jReader = Json.createReader(reader)) {
            return jReader.readObject();
        }
    }

}