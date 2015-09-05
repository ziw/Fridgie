package ying.zi.fridgie.controller;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by ziw on 9/5/2015.
 */
public class DataFetchTask extends AsyncTask<DataFetchTask.Task, Void, Object> {

    private Activity callerActitiy;

    private DataFetchTask(){}

    public DataFetchTask(Activity activity){
        callerActitiy = activity;
    }

    @Override
    protected Object doInBackground(Task... params) {
        Task task = params[0];
        switch (task){
            case GET_ALL_RECORDS:
            default: return null;
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    public static enum Task
    {
        GET_ALL_RECORDS
    }
}
