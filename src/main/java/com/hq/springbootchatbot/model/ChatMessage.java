package com.hq.springbootchatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize(builder = ChatMessage.Builder.class)
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private String model;

    public ChatMessage() {}

    private ChatMessage(Builder builder) {
        this.text = builder.text;
        this.model = builder.model;
    }

    public String getText() {
        return text;
    }

    public ChatMessage setText(String text) {
        this.text = text;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ChatMessage setModel(String model) {
        this.model = model;
        return this;
    }

    public static class Builder {
        @JsonProperty("text")
        private String text;

        @JsonProperty("model")
        private String model;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(this);
        }
    }
}
