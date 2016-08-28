package com.wukker.sb.eventconnectionfortopmobile.model;

import com.wukker.sb.eventconnectionfortopmobile.model.Error;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sb on 06.11.15.
 */
public class Response<T extends Base> implements Serializable {

    private boolean hasErrors;
    private ArrayList<Error> errors;
    private ArrayList<T> response;

    public ArrayList<T> getResponse() {
        return response;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }
}
