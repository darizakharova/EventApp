package com.wukker.sb.eventconnectionfortopmobile.model.methods;

import android.app.Activity;
import android.content.SharedPreferences;

import com.wukker.sb.eventconnectionfortopmobile.model.methods.SharedPreferencesFields;

import org.json.JSONObject;

/**
 * Created by sb on 04.11.15.
 */
public class SPHelperParams {
    private SharedPreferencesFields field;
    String toSaveString;
    JSONObject toSaveJSONObject;
    long toSaveLong;
    boolean toSaveBoolean;
    int toSaveInt;

    String name;
    SharedPreferences sharedPreferences;

    public SPHelperParams(SharedPreferencesFields field, SharedPreferences sharedPreferences, String name) {
        this.field = field;
        this.sharedPreferences = sharedPreferences;
        this.name = name;
    }

    public SPHelperParams(JSONObject toSaveJSONObject, String name, SharedPreferences sharedPreferences) {
        this.field = SharedPreferencesFields.JSONOBJECT;
        this.toSaveJSONObject = toSaveJSONObject;
        this.name = name;
        this.sharedPreferences = sharedPreferences;
    }

    public SPHelperParams(String toSaveString, String name, SharedPreferences sharedPreferences) {
        this.field = SharedPreferencesFields.STRING;
        this.toSaveString = toSaveString;
        this.name = name;
        this.sharedPreferences = sharedPreferences;
    }

    public SPHelperParams(long toSaveLong, String name, SharedPreferences sharedPreferences) {
        this.field = SharedPreferencesFields.LONG;
        this.toSaveLong = toSaveLong;
        this.name = name;
        this.sharedPreferences = sharedPreferences;
    }

    public SPHelperParams(boolean toSaveBoolean, String name, SharedPreferences sharedPreferences) {
        this.field = SharedPreferencesFields.BOOLEAN;
        this.toSaveBoolean = toSaveBoolean;
        this.name = name;
        this.sharedPreferences = sharedPreferences;
    }

    public SPHelperParams(int toSaveInt, String name, SharedPreferences sharedPreferences) {
        this.field = SharedPreferencesFields.INT;
        this.toSaveInt = toSaveInt;
        this.name = name;
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferencesFields getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public String getToSaveString() {
        return toSaveString;
    }

    public JSONObject getToSaveJSONObject() {
        return toSaveJSONObject;
    }

    public long getToSaveLong() {
        return toSaveLong;
    }

    public boolean isToSaveBoolean() {
        return toSaveBoolean;
    }

    public int getToSaveInt() {
        return toSaveInt;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
