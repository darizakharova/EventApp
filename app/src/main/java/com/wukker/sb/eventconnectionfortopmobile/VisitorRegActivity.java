package com.wukker.sb.eventconnectionfortopmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONSerializationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.VisitorType;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.URLHelper;

import java.util.ArrayList;
import java.util.List;

public class VisitorRegActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private User user;
    private Spinner spinner;
    private EditText firstName;
    private EditText lastName;
    private EditText middleName;
    private EditText companyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //marking up fields
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_reg);
        spinner = (Spinner)findViewById(R.id.spinnerparticipant);
        firstName = (EditText)findViewById(R.id.textView2);
        lastName = (EditText)findViewById(R.id.textView3);
        middleName = (EditText)findViewById(R.id.textView4);
        companyName = (EditText)findViewById(R.id.editText);

        //initializing user from saved SP
        sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
        user = SharedPreferencesBrain.readUser(sharedPreferences);

        //filling up fields
        if (user != null) {
            firstName.setText(user.getFirstname());
            lastName.setText(user.getLastname());
            middleName.setText(user.getMiddlename());
            companyName.setText(user.getCompanyName());
        } else {
            throw new NullPointerException();
        }

        //filling up spinner
        List<String> visitorTypeArray = VisitorType.getAsArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                visitorTypeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visitor_reg, menu);
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

    public void onClick(View view) {
        final String selected = spinner.getSelectedItem().toString();

        user.setFirstname(firstName.getText().toString());
        user.setLastname(lastName.getText().toString());
        user.setMiddlename(middleName.getText().toString());
        user.setCompanyName(companyName.getText().toString());

        JSONSerializationBrain.postUserAsync(user, new URLHelper() {
            @Override
            protected void onPostExecute(final String response) {
                JSONSerializationBrain.postStatusAsync(selected, user, new URLHelper() {
                    @Override
                    protected void onPostExecute(String _) {
                        User transUser = JSONDeserialaizationBrain.getUserFromResponse(response);
                        user.setId(transUser.getId());
                        SharedPreferencesBrain.saveUser(user, sharedPreferences);
                        if (user != null) {
                            SharedPreferencesBrain.hasVisited(true, getSharedPreferences(Constants.hasVisited,
                                    Context.MODE_PRIVATE));
                            Intent intent = new Intent(VisitorRegActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
