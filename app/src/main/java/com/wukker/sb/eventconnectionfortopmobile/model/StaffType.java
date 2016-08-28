package com.wukker.sb.eventconnectionfortopmobile.model;

import java.util.ArrayList;

/**
 * Created by sb on 02.11.15.
 */
public enum StaffType {SPEAKER, MODERATOR;
    public static ArrayList<String> getAsArray()
    {
        ArrayList<String> result = new ArrayList<>();
        result.add("Спикер");
        result.add("Модератор");

        return result;
    }


    @Override
    public String toString() {

        ArrayList<String> values = this.getAsArray();
        switch (this)
        {
            case SPEAKER:
            {
                return values.get(0);
            }
            case MODERATOR:
            {
                return values.get(1);
            }
            default:
            {
                return "error";
            }

        }
    }

    public static StaffType getFromString (String value)
    {
        ArrayList<String> values = getAsArray();

        if (value.equals(values.get(0)))
            return SPEAKER;
        else if (value.equals(values.get(1)))
            return MODERATOR;
        else return SPEAKER;
    }
}
