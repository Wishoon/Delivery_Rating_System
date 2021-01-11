package com.db2020.delivery.entity;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringWriter;

//@Data
//@ToString
//@NoArgsConstructor
public class ChatMessage {

//    private String member_id;
//    private String member_nm;
//    private String member_grade;
//    private String member_score;
//    private String member_caution;

    private JsonObject json;

    public ChatMessage(JsonObject json) {
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public String toString(){
        StringWriter writer = new StringWriter();

        Json.createWriter(writer).write(json);

        return writer.toString();
    }
}
