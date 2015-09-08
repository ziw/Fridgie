package ying.zi.fridgie.controller;

import android.content.Context;
import android.os.AsyncTask;

import ying.zi.fridgie.db.FridgieDataSource;
import ying.zi.fridgie.model.InventoryRecord;

public class DataFetchTask extends AsyncTask<DataFetchTask.Task, Void, Object> {

    private DataFetchingUIActivity callerActitiy;
    private Task task;
    private Context context;


    public static class Task{
        private TaskType taskType;
        private Object param;

        public Task(TaskType task, Object param){
            this.taskType = task;
            this.param = param;
        }

        public TaskType getTaskType() {
            return taskType;
        }

        public Object getParam() {
            return param;
        }

        public enum TaskType
        {
            GET_ALL_RECORDS,
            DELETE_RECORD
        }

    }

    public interface DataFetchingUIActivity {
        void handleDataFetchTaskResult(Task task, Object result);
    }

    private DataFetchTask(){}

    public DataFetchTask(DataFetchingUIActivity activity, Context context){
        this.callerActitiy = activity;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Task... params) {
        task = params[0];

        switch (task.taskType){
            case GET_ALL_RECORDS : return getAllRecords();
            case DELETE_RECORD : return deleteRecord(task.param);
            default: return null;
        }

    }

    @Override
    protected void onPostExecute(Object result) {
        callerActitiy.handleDataFetchTaskResult(task, result);
    }

    private Object getAllRecords(){
        FridgieDataSource ds = FridgieDataSource.getInstance(context);
        return ds.getAllRecords();
    }

    private Object deleteRecord(Object record){
        FridgieDataSource ds = FridgieDataSource.getInstance(context);
        InventoryRecord r = (InventoryRecord)record;
        return ds.deleteInventoryRecord(r);
    }


}
