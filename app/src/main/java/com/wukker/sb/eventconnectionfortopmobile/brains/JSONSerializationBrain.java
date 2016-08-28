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

    public static String postUser(User user)
    {
        URL url = null;
        JSONObject userAsJson = null;

        try {
            Gson gson = new GsonBuilder().create();
            String userAsString = gson.toJson(user);
            userAsJson = new JSONObject(userAsString);
        } catch (JSONException e)
        {
            e.printStackTrace();
            //TODO
        }

        String response = null;
        URLHelper urlHelper = new URLHelper();
        try {url = new URL(Constants.globalURL+Constants.userPointer);} catch (MalformedURLException e){ e.printStackTrace();}
        HelperParams helperParams = new HelperParams(url, HTTPMethod.POST, userAsJson);
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

    public static void postStatus(String selected, User user)
    {
        URL url = null;
        HelperParams helperParams;
        try {
            url = new URL(Constants.globalURL+Constants.userPointer + user.getOauthToken() + Constants.visitPointer + Constants.conferceID);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            //TODO
        }
        helperParams = new HelperParams(url, HTTPMethod.POSTFORM, "type="+ VisitorType.getFromString(selected).toString());
       try {
           URLHelper urlHelper = new URLHelper();
           urlHelper.execute(helperParams);
           String response = urlHelper.get();
       } catch (InterruptedException e)
       {
           e.printStackTrace();
       } catch (ExecutionException e)
       {
           e.printStackTrace();
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
        if (staffID == Constants.enough)
        {
            try {url = new URL(Constants.globalURL+Constants.eventPointer + eventID + Constants.ratePointer);} catch (MalformedURLException e){ e.printStackTrace();}
        } else {
            try {url = new URL(Constants.globalURL+Constants.eventPointer + eventID + Constants.staffPointer + staffID + Constants.ratePointer);} catch (MalformedURLException e){ e.printStackTrace();}
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

    public static String putQuest (Questionnaire questionnaire, User user)
    {
        URL url = null;
        JSONObject questAsJson = null;

        try {
            Gson gson = new GsonBuilder().create();
            String ratingAsString = gson.toJson(questionnaire);
            questAsJson = new JSONObject(ratingAsString);

        } catch (JSONException e)
        {
            e.printStackTrace();
            //TODO
        }

        String response = null;
        URLHelper urlHelper = new URLHelper();
            try {url = new URL(Constants.globalURL+Constants.userPointer + user.getOauthToken() + Constants.questPointer);} catch (MalformedURLException e){ e.printStackTrace();}


        HelperParams helperParams = new HelperParams(url, HTTPMethod.PUT, questAsJson);
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

}