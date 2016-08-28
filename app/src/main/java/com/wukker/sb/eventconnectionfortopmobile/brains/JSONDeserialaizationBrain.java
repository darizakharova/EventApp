package com.wukker.sb.eventconnectionfortopmobile.brains;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wukker.sb.eventconnectionfortopmobile.model.Conference;
import com.wukker.sb.eventconnectionfortopmobile.model.Event;
import com.wukker.sb.eventconnectionfortopmobile.model.Response;
import com.wukker.sb.eventconnectionfortopmobile.model.Staff;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.HTTPMethod;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.HelperParams;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.URLHelper;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sb on 07.11.15.
 */
public class JSONDeserialaizationBrain {

    public static Conference getConference (long conferenceID) {

        URLHelper urlHelper;
        String jsConference = null;
        ArrayList<Conference> ourConferenceList = new ArrayList<Conference>();
        Conference ourConference = null;
        Response<Conference> conferenceResponse;

        try {


            HelperParams getConference = new HelperParams(new URL(Constants.globalURL + Constants.conferencePointer + conferenceID), HTTPMethod.GET);
            urlHelper = new URLHelper();
            urlHelper.execute(getConference);
            jsConference = urlHelper.get();

            Gson gson = new Gson();
            Type innerType = new TypeToken<Response<Conference<Event>>>() {
            }.getType();
            conferenceResponse = gson.fromJson(jsConference, innerType);
            ourConferenceList = conferenceResponse.getResponse();
            ourConference = ourConferenceList.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ourConference;
    }

    public static Staff getStaff (Event event, int n)
    {
        return event.getAllStaff().get(n);
    }

    public static Event getEvent (int n)
    {

        return JSONDeserialaizationBrain.getEventList(Constants.conferenceID).get(n);
    }

    public static ArrayList<String> getEventForListView (Conference conference)
    {

        ArrayList<String> eventsAsString = new ArrayList<>();
        ArrayList<Event> allEvents = conference.getAllEvents();
        for (Event event : allEvents)
        {
            eventsAsString.add(event.toRString());
        }

        return eventsAsString;
    }

    public static ArrayList<String> getStaffForListView (Event event)
    {
        Conference conference = JSONDeserialaizationBrain.getConference(Constants.conferenceID);

        ArrayList<String> eventsAsString = new ArrayList<>();
        ArrayList<Staff> allStaff = event.getAllStaff();
        for (Staff staff : allStaff)
        {
            eventsAsString.add(staff.toListString());
        }

        return eventsAsString;
    }

    public static User getUserFromResponse (String response)
    {
        Response<User> response1;
        ArrayList<User> users;
        Gson gson = new Gson();
        Type innerType = new TypeToken<Response<User>>() {
        }.getType();
        response1 = gson.fromJson(response, innerType);
        users = response1.getResponse();
        return users.get(0);
    }

    public static ArrayList<User> getUserListFromResponse (String response)
    {
        Response response1;
        Gson gson = new Gson();
        Type innerType = new TypeToken<Response<User>>() {
        }.getType();
        response1 = gson.fromJson(response, innerType);
        return response1.getResponse();
    }

    public static String getResponse (long conferenceId)
    {
        try{
            URL url = new URL(Constants.globalURL + Constants.conferencePointer + conferenceId + Constants.userIknowIwrongButINeedThisPointer);
        HelperParams getConference = new HelperParams(url, HTTPMethod.GET);
            System.out.println(url);
        URLHelper urlHelper = new URLHelper();
        urlHelper.execute(getConference);
        return urlHelper.get();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Event> getEventList (Long conferenceID)
    {
        URLHelper urlHelper;
        String jsConference = null;
        ArrayList<Conference> ourConferenceList = new ArrayList<Conference>();
        Conference ourConference = null;
        Response<Conference> conferenceResponse;


        try {


            HelperParams getConference = new HelperParams(new URL(Constants.globalURL + Constants.conferencePointer + conferenceID), HTTPMethod.GET);
            urlHelper = new URLHelper();
            System.out.println(getConference.getUrl());
            urlHelper.execute(getConference);
            jsConference = urlHelper.get();

            Gson gson = new Gson();
            Type innerType = new TypeToken<Response<Conference<Event>>>() {
            }.getType();
            conferenceResponse = gson.fromJson(jsConference, innerType);
            ourConferenceList = conferenceResponse.getResponse();
            ourConference = ourConferenceList.get(0);
            System.out.println(ourConference.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ourConference.getAllEvents();
    }
}
