package com.wukker.sb.eventconnectionfortopmobile.model.methods;

import android.os.AsyncTask;

/**
 * Created by sb on 08.11.15.
 */
public class SharedPreferencesReader extends AsyncTask<SPHelperParams, Void, String> {
    public SharedPreferencesReader() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(SPHelperParams... params) {
        String result = null;
        for (SPHelperParams sphelperParam: params) {
            switch (sphelperParam.getField()){
                case STRING:
                    result = sphelperParam.getSharedPreferences().getString(sphelperParam.getName(), " "); break;
                case JSONOBJECT:
                    result = sphelperParam.getSharedPreferences().getString(sphelperParam.getName(), " "); break;
                case BOOLEAN:
                {Boolean temp = sphelperParam.getSharedPreferences().getBoolean(sphelperParam.getName(), true);
                    if (temp) {result =  "true";}
                    else {result = "false";}
                    break;}
                case INT:
                {result =  sphelperParam.getSharedPreferences().getLong(sphelperParam.getName(), 0)+""; break;}
                case LONG:
                {result =  sphelperParam.getSharedPreferences().getLong(sphelperParam.getName(), 0)+""; break;}
                default:
                    result = Constants.ERROR_BASIC_MESSAGE;

            }

        }
        return result;
    }
}
