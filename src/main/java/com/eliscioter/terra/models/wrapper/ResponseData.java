package com.eliscioter.terra.models.wrapper;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
    private final Map<String, Object> data;

    public ResponseData() {
        this.data = new HashMap<>();
    }

    public ResponseData add(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
