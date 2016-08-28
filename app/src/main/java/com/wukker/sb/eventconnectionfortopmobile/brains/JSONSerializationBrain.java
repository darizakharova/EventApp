package com.wukker.sb.eventconnectionfortopmobile.brains;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wukker.sb.eventconnectionfortopmobile.model.Questionnaire;
import com.wukker.sb.eventconnectionfortopmobile.model.Rating;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.VisitorType;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.HTTPMethod;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.HelperParams;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.URLHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by sb on 07.11.15.
 */
public class JSONSerializationBrain {

    public static void postUserAsync(User user, URLHelper urlHelper){
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject userAsJson = new JSONObject(gson.toJson(user));
            URL url = new URL(Constants.GLOBAL_URL +Constants.USER_POINTER);
            HelperParams helperParams = new HelperParams(url, HTTPMethod.POST, userAsJson);
            urlHelper.execute(helperParams);
        } catch (Throwable e) {
            throw new RuntimeException();
            //TODO
        }
    }


    public static void postStatusAsync(String selected, User user, URLHelper urlHelper) {
        try {
            URL url = new URL(Constants.GLOBAL_URL +Constants.USER_POINTER + user.getOauthToken() +
                    Constants.VISIT_POINTER + Constants.CONFERCE_ID);
            HelperParams helperParams = new HelperParams(url, HTTPMethod.POSTFORM, "type="+
                    VisitorType.getFromString(selected).toString());
            urlHelper.execute(helperParams);
        } catch (MalformedURLException  e) {
            e.printStackTrace();
            //TODO
        }
    }



    public static String putRating (Rating rating, long eventID, long staffID)
    {
        URL url = null;
        JSONObject ratingAsJson = null;

        try {
            Gson gson = new GsonBuilder().create();
            String ratingAsString = gson.toJson(rating);
            ratingAsJson = new JSONObject(ratingAsString);

        } catch (JSONException e)
        {
            e.printStackTrace();
            //TODO
        }

        String response = null;
        URLHelper urlHelper = new URLHelper();
        if (staffID == Constants.TIMEOUT)            // WTF???
        {
            try {url = new URL(Constants.GLOBAL_URL +Constants.EVENT_POINTER + eventID + Constants.RATE_POINTER);}
            catch (MalformedURLException e){ e.printStackTrace();}
        } else {
            try {url = new URL(Constants.GLOBAL_URL +Constants.EVENT_POINTER + eventID + Constants.STAFF_POINTER
                    + staffID + Constants.RATE_POINTER);}
            catch (MalformedURLException e){ e.printStackTrace();}
        }

        HelperParams helperParams = new HelperParams(url, HTTPMethod.PUT, ratingAsJson);
        try {
            urlHelper.execute(helperParams);
            response = urlHelper.get();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return response;
    }

    public static String putQuest (Questionnaire questionnaire, User user) {
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject questAsJson = new JSONObject(gson.toJson(questionnaire));
            URLHelper urlHelper = new URLHelper();
            URL url = new URL(Constants.GLOBAL_URL +Constants.USER_POINTER + user.getOauthToken() +
                    Constants.QUEST_POINTER);
            HelperParams helperParams = new HelperParams(url, HTTPMethod.PUT, questAsJson);
            urlHelper.execute(helperParams);
            return  urlHelper.get(); // TODO async
        } catch (ExecutionException |InterruptedException | JSONException | MalformedURLException e)
        {
            throw new RuntimeException();
        }
    }

}