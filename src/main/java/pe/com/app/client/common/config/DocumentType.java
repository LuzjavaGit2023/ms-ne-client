package pe.com.app.client.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentType {
    DNI("DNI"),
    RUC("RUC");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static DocumentType fromString(String value) {
        return value != null ? DocumentType.valueOf(value.toUpperCase()) : null;
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}