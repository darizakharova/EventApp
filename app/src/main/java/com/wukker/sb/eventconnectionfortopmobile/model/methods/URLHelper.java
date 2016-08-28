package com.wukker.sb.eventconnectionfortopmobile.model.methods;

import android.os.AsyncTask;

import com.wukker.sb.eventconnectionfortopmobile.model.methods.HTTPMethod;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.HelperParams;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by sb on 02.11.15.
 */
public class URLHelper extends AsyncTask<HelperParams, Void, String>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String response) {
        super.onCancelled(response);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(HelperParams[] helperParamses) {

        String response = " ";
        for (HelperParams helperParams: helperParamses) {


            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) helperParams.getUrl().openConnection();
                httpURLConnection.setConnectTimeout((int)Constants.enough);
                switch (helperParams.getHttpMethod()) {

                    case POST: {

                        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        httpURLConnection.setRequestMethod(HTTPMethod.POST.toString());
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);


                        DataOutputStream postToURL = new DataOutputStream(httpURLConnection.getOutputStream());

                        postToURL.write(helperParams.getJsonObject().toString().getBytes("UTF-8"));
                        postToURL.flush();
                        postToURL.close();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {

                            sb.append(line);

                        }

                        response = sb.toString();
                        reader.close();


                        httpURLConnection.disconnect();
                        break;
                    }
                    case POSTFORM: {

                        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                        httpURLConnection.setRequestMethod(HTTPMethod.POST.toString());
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setUseCaches(false);

                        DataOutputStream postToURL = new DataOutputStream(httpURLConnection.getOutputStream());

                        postToURL.write(helperParams.getString().getBytes("UTF-8"));
                        postToURL.flush();

                        response = httpURLConnection.getResponseCode() + "";
                        postToURL.close();
                        httpURLConnection.disconnect();
                        break;
                    }

                    case GET: {

                        httpURLConnection.connect();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {

                            sb.append(line);

                        }

                        response = sb.toString();
                        reader.close();

                        httpURLConnection.disconnect();
                        break;
                    }

                    case PUT: {

                        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        httpURLConnection.setRequestMethod(HTTPMethod.PUT.toString());
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setUseCaches(false);

                        DataOutputStream postToURL = new DataOutputStream(httpURLConnection.getOutputStream());

                        postToURL.write(helperParams.getJsonObject().toString().getBytes("UTF-8"));
                        postToURL.flush();
                        postToURL.close();


                        response = httpURLConnection.getResponseMessage()+"";
                        httpURLConnection.disconnect();
                        break;
                    }

                    default: break;
                }

            }
            catch (NullPointerException e)
            {
                //TODO
                e.printStackTrace();
            }

            catch (IOException e) {
                //TODO
                e.printStackTrace();


            }

        }


        return response;

    }


}
