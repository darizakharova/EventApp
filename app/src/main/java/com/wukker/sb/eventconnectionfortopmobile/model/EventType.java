package com.wukker.sb.eventconnectionfortopmobile.model;

import java.util.ArrayList;

/**
 * Created by sb on 02.11.15.
 */
public enum EventType {
    ORGANIZATIONAL,
    PLENARY,
    SECTION;

    public static ArrayList<String> getAsArray()
    {
        ArrayList<String> result = new ArrayList<>();
        result.add("Организационная часть");
        result.add("Пленар");
        result.add("Секция");


        return result;
    }


    @Override
    public String toString() {

        ArrayList<String> values = this.getAsArray();
        switch (this)
        {
            case ORGANIZATIONAL:
            {
                return values.get(0);
            }
            case PLENARY:
            {
                return values.get(1);
            }
            case SECTION:
            {
                return values.get(2);
            }

            default:
            {
                return "error";
            }

        }
    }

    public static EventType getFromString (String value)
    {
        ArrayList<String> values = getAsArray();

        if (value.equals(values.get(0)))
            return ORGANIZATIONAL;
        else if (value.equals(values.get(1)))
            return PLENARY;
        else if (value.equals(values.get(2)))
            return SECTION;
        else return ORGANIZATIONAL;
    }
}
