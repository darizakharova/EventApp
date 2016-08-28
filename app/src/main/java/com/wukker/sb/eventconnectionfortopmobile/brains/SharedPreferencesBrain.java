package com.wukker.sb.eventconnectionfortopmobile.brains;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.SPHelperParams;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.SharedPreferencesFields;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.SharedPreferencesHelper;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.SharedPreferencesReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by sb on 07.11.15.
 */
public class SharedPreferencesBrain {

    public SharedPreferences setEventConnectionSP(SPHelperParams spHelperParams) {




        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        sharedPreferencesHelper.execute(spHelperParams);


        return spHelperParams.getSharedPreferences();
    }

    public static void saveUser (User user, SharedPreferences sharedPreferences)
    {

        //Convert user to json
        JSONObject userAsJson = null;

        try {
            Gson userBuilder = new GsonBuilder().setPrettyPrinting().create();
            String userToJson = userBuilder.toJson(user);
            userAsJson = new JSONObject(userToJson);

        } catch (JSONException e) {
            //TODO
            e.printStackTrace();
        }


        //Saving user in SharedPreferences
        SPHelperParams userSPHelper = new SPHelperParams(userAsJson, Constants.name, sharedPreferences);
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        sharedPreferencesHelper.execute(userSPHelper);

    }

    public static User readUser(SharedPreferences sharedPreferences)
    {
        try {
            SPHelperParams spHelperParams = new SPHelperParams(SharedPreferencesFields.STRING, sharedPreferences, Constants.name);
            SharedPreferencesReader sharedPreferencesReader = new SharedPreferencesReader();
            sharedPreferencesReader.execute(spHelperParams);
            String userAsJson = sharedPreferencesReader.get();
            Gson gson = new Gson();
            return gson.fromJson(userAsJson, User.class);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            return null;
            //TODO
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
            return null;
            //TODO
        }
    }

    public static void hasVisited (Boolean hasVisited, SharedPreferences sharedPreferences)
    {
        SPHelperParams userSPHelper = new SPHelperParams(hasVisited, Constants.hasVisited, sharedPreferences);
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        sharedPreferencesHelper.execute(userSPHelper);

    }

    public static int getRating (String name, SharedPreferences sharedPreferences)
    {
            int getInt;
            try {
                SPHelperParams spHelperParams = new SPHelperParams(SharedPreferencesFields.INT, sharedPreferences, name);
                SharedPreferencesReader sharedPreferencesReader = new SharedPreferencesReader();
                sharedPreferencesReader.execute(spHelperParams);
                getInt = Integer.parseInt(sharedPreferencesReader.get());
                return getInt;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
                //TODO
            } catch (ExecutionException e) {
                e.printStackTrace();
                return 0;
                //TODO
            }

    }

    public static void saveRating (int rating, SharedPreferences sharedPreferences, String name)
    {


        //Saving user in SharedPreferences
        SPHelperParams userSPHelper = new SPHelperParams(rating, name, sharedPreferences);
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        sharedPreferencesHelper.execute(userSPHelper);

    }
}
