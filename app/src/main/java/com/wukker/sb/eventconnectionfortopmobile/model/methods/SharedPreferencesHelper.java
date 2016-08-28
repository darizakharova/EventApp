package com.wukker.sb.eventconnectionfortopmobile.model.methods;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.SPHelperParams;

/**
 * Created by sb on 04.11.15.
 */
public class SharedPreferencesHelper extends AsyncTask<SPHelperParams, Void, Void>{

    public class SharedPreferencesException extends Exception
    {
        public static final String conclusion = Constants.errorBasicMessage + Constants.errorWithSaveFile;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(SPHelperParams... spHelperParamses)
    {
        try {
            for (SPHelperParams spHelperParams : spHelperParamses) {
                SharedPreferences.Editor editor = spHelperParams.getSharedPreferences().edit();
                switch (spHelperParams.getField()) {
                    case JSONOBJECT: {
                        editor.putString(spHelperParams.getName(), spHelperParams.getToSaveJSONObject().toString());
                        break;
                    }
                    case STRING: {
                        editor.putString(spHelperParams.getName(), spHelperParams.getToSaveString());
                        break;
                    }
                    case INT: {
                        editor.putInt(spHelperParams.getName(), spHelperParams.getToSaveInt());
                        break;
                    }
                    case LONG: {
                        editor.putLong(spHelperParams.getName(), spHelperParams.getToSaveLong());
                        break;
                    }
                    case BOOLEAN: {
                        editor.putBoolean(spHelperParams.getName(), spHelperParams.isToSaveBoolean());
                        break;
                    }
                    default: {
                        //TODO
                        throw new SharedPreferencesException();
                    }
                }
                editor.apply();
            }
        }catch (SharedPreferencesException e)
        {
            //TODO
            e.printStackTrace();

        }
        return null;
    }
}
