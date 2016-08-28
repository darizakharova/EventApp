package com.wukker.sb.eventconnectionfortopmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

import org.json.JSONObject;

public class SNRegistrationActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private VKAccessToken vkAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_snregistration);
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                if (currentAccessToken != null) {
                    accessToken = currentAccessToken;
                    processAccessToken();
                }
            }
        };
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            processAccessToken();
        }

        //vkAccessTokenTracker.startTracking();
        //VKSdk.initialize(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_snregistration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode,data);
    }

    private void processAccessToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
        User last_user = SharedPreferencesBrain.readUser(sharedPreferences);
        String last_fb_id;
        Intent intent;
        String fbID = Constants.FB_PREFIX + accessToken.getUserId();
        if (last_user != null) {
            last_fb_id = last_user.getSocialID();
            if (fbID.equals(last_fb_id)) {
                intent = new Intent(SNRegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                return;
            }
        }

        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String fbID = Constants.FB_PREFIX + accessToken.getUserId();
                            String[] name = object.getString("name").split(" ");
                            long oauthTokenExpireDate = (accessToken.getExpires().getTime()) / 1000;
                            User user = new User(name[0],name[1],accessToken.getToken(),
                                    oauthTokenExpireDate,fbID);
                            SharedPreferences sharedPreferences =
                                    getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
                            SharedPreferencesBrain.saveUser(user, sharedPreferences);
                            Intent intent = new Intent(SNRegistrationActivity.this,
                                            VisitorRegActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {}
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void processVKAccessToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
        User last_user = SharedPreferencesBrain.readUser(sharedPreferences);
        String last_vk_id;
        Intent intent;
        String vkID = Constants.VK_PREFIX + vkAccessToken.userId;
        if (last_user != null) {
            last_vk_id = last_user.getSocialID();
            if (vkID.equals(last_vk_id)) {
                intent = new Intent(SNRegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                return;
            }
        }

        VKRequest request = VKApi.users().get();
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    String vkID = Constants.VK_PREFIX + vkAccessToken.userId;
                    String first_name = response.json.getJSONArray("response").getJSONObject(0).getString("first_name");
                    String last_name = response.json.getJSONArray("response").getJSONObject(0).getString("last_name");
                    long oauthTokenExpireDate = vkAccessToken.expiresIn; //TODO
                    User user = new User(first_name,last_name,vkAccessToken.accessToken,
                            oauthTokenExpireDate,vkID);
                    SharedPreferences sharedPreferences =
                            getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
                    SharedPreferencesBrain.saveUser(user, sharedPreferences);
                    Intent intent = new Intent(SNRegistrationActivity.this,
                            VisitorRegActivity.class);
                    startActivity(intent);
                } catch (Exception e) {}
            }
        });
    }

    /*KAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                throw new RuntimeException(); //TODO
            } else {
                vkAccessToken = newToken;
                processVKAccessToken();
            }
        }
    };

    public void onVKButtonClick(View view) {
        VKSdk.login(this);
    }*/
}
