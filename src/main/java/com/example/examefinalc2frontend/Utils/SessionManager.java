package com.example.examefinalc2frontend.Utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.prefs.Preferences;

public class SessionManager {
    private static SessionManager instance;
    @Setter
    @Getter
    private String token;
    @Setter
    @Getter
    private String username;
    private final SimpleStringProperty messageProperty = new SimpleStringProperty("");

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void clearSession() {
        this.token = null;
        this.username = null;
        messageProperty.set("");
    }

    public boolean hasActiveSession() {
        return token != null && !token.isEmpty();
    }

    // Útil para mostrar mensajes en la UI
    public StringProperty messageProperty() {
        return messageProperty;
    }

    public void setMessage(String message) {
        messageProperty.set(message);
    }

    public void saveSession() {
        Preferences prefs = Preferences.userNodeForPackage(SessionManager.class);
        prefs.put("token", token != null ? token : "");
        prefs.put("username", username != null ? username : "");
    }

    public void loadSession() {
        Preferences prefs = Preferences.userNodeForPackage(SessionManager.class);
        token = prefs.get("token", null);
        username = prefs.get("username", null);
    }
}
