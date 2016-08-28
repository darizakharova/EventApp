package com.wukker.sb.eventconnectionfortopmobile.model;

import java.util.ArrayList;

/**
 * Created by sb on 02.11.15.
 */
public enum VisitorType {
    PARTICIPANT,
    IT_COMPANY,
    MEDIA,
    OTHER;

    public static ArrayList<String> getAsArray()
    {
        ArrayList<String> result = new ArrayList<>();
        result.add("Участник");
        result.add("ИТ-Компания");
        result.add("СМИ");
        result.add("Другой статус");

        return result;
    }



    public String toRString() {

        ArrayList<String> values = VisitorType.getAsArray();
        switch (this)
        {
            case PARTICIPANT:
            {
                return values.get(0);
            }
            case IT_COMPANY:
            {
                return values.get(1);
            }
            case MEDIA:
            {
                return values.get(2);
            }
            case OTHER:
            {
                return values.get(3);
            }
            default:
            {
                return "error";
            }

        }
    }

    public static VisitorType getFromString (String value)
    {
        ArrayList<String> values = getAsArray();

        if (value.equals(values.get(0)))
            return PARTICIPANT;
        else if (value.equals(values.get(1)))
            return IT_COMPANY;
        else if (value.equals(values.get(2)))
            return MEDIA;
        else return OTHER;
    }
}
