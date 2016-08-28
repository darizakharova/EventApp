package com.wukker.sb.eventconnectionfortopmobile.model;

import java.io.Serializable;

/**
 * Created by sb on 06.11.15.
 */
public class Error implements Serializable {
    private boolean fatal;
    private String message;

    public Error(boolean fatal, String message) {
        this.fatal = fatal;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFatal() {
        return fatal;
    }
}

