package com.eliscioter.terra.model.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ResponseWrapper<T> {
    private boolean success;
    private T data;
    private String message;

    @JsonCreator
    public ResponseWrapper(
            @JsonProperty("success") boolean success,
            @JsonProperty("data") T data,
            @JsonProperty("message") String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    @JsonProperty("data")
    public T getData() {
        return data;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}
