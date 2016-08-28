package com.wukker.sb.eventconnectionfortopmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

public class SNRegistrationActivity extends AppCompatActivity {

//Data for user-creating
    private User visitorAsUser;
    private String allName;
    private String oauthToken;
    private String socialID;
    private long oauthTokenExpireDate;

//Data for FBSDK
    private UiLifecycleHelper uiHelper;
    private LoginButton loginButtonFB;
    RelativeLayout mRelativeLayout;


//For long-term keeping on date
    SharedPreferences sharedPreferences;

//FBSDK session
    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {
                oauthToken = session.getAccessToken();
                oauthTokenExpireDate = (session.getExpirationDate().getTime()) / 1000;

                Log.d("FacebookSampleActivity", "Facebook session opened");
            } else if (state.isClosed()) {
                Log.d("FacebookSampleActivity", "Facebook session closed");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(this, statusCallback);
        uiHelper.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snregistration);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


        loginButtonFB = (LoginButton) findViewById(R.id.fb_login_button);
        loginButtonFB.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
//Flag for successfull user registration;
                try {
                    if (user != null) {
//receive user IDs
                        sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
                        allName = user.getName();
                        socialID = Constants.fbPrefix + user.getId();
                        String[] names = allName.split(Constants.space);
                        visitorAsUser = new User(names[0], names[1], oauthToken, oauthTokenExpireDate, socialID);
//Saving user data
                        SharedPreferencesBrain.saveUser(visitorAsUser, sharedPreferences);
//Jump to next screen
                        Intent intent = new Intent(SNRegistrationActivity.this, VisitorRegActivity.class);
                        startActivity(intent);
                    } else {
                        throw new Exception();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

        });


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

    //returning control to main thread
    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    //по возвращению обратно на активность передаем все полученные данные с диалога в колбек
    // и живем дальше счастливо
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("In result");
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    //при вращении экрана и т д сохраняем все что происходит на экране,
    // а то активити обычно обновляется. а диалог останется жив
    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        uiHelper.onSaveInstanceState(savedState);
    }
}
