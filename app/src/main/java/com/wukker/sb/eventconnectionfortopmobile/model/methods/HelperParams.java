package com.wukker.sb.eventconnectionfortopmobile.model.methods;

import com.wukker.sb.eventconnectionfortopmobile.model.methods.HTTPMethod;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by sb on 02.11.15. IT IS URL HELPER PARAMETERS. I NAMED IT IN THE VERY BEGINING OF PROJECT. SO, KEEP IT IN MIND.
 */
public class HelperParams {
    URL url;
    HTTPMethod httpMethod;
    JSONObject jsonObject;
    String string;


    public HelperParams(URL url, HTTPMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public HelperParams(URL url, HTTPMethod httpMethod, JSONObject jsonObject) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.jsonObject = jsonObject;
    }

    public HelperParams(URL url, HTTPMethod httpMethod, String string) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.string = string;
    }

    public URL getUrl() {
        return url;
    }

    public HTTPMethod getHttpMethod() {
        return httpMethod;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getString() {
        return string;
    }
}
