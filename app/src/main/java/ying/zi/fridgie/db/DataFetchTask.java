package ying.zi.fridgie.db;

import android.content.Context;
import android.os.AsyncTask;

import ying.zi.fridgie.model.InventoryRecord;

/**
 * An asynchronous task to handle database CRUD operations.
 * All DB ops should be done through this class.
 * Execution takes a Task object to specify parameters and task type.
 * The task does not handle post execution actions.
 * It only calls DataFetchingUIActivity#handleDataFetchTaskResult with the result.
 *
 */
public class DataFetchTask extends AsyncTask<DataFetchTask.Task, Void, Object> {

    private DataFetchingUIActivity callerActitiy;
    private Task task;
    private Context context;

    /**
     * Task supported by DataFetchTask. Carries task type and parameter
     */
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
            DELETE_RECORD,
            INSERT_RECORD,
            REDUCE_RECORD,
        }

    }

    /**
     * DataFetchTask callback listener interface.
     * UI activity that uses DataFetchTask should implement this interface
     * to handle callback.
     */
    public interface DataFetchingUIActivity {

        Context getUIContext();

        /**
         * Call back method called by DataFetchTask after doInBackground completes.
         *
         * @param task The original task given during task execution
         * @param result The execution result
         */
        void handleDataFetchTaskResult(Task task, Object result);

        void handleDataFetchTaskException(Task task, Throwable e);
    }

    private DataFetchTask(){}

    public DataFetchTask(DataFetchingUIActivity activity){
        this.callerActitiy = activity;
        this.context = activity.getUIContext();
    }

    @Override
    protected Object doInBackground(Task... params) {
        task = params[0];

        switch (task.taskType){
            case GET_ALL_RECORDS : return getAllRecords();
            case DELETE_RECORD : return deleteRecord(task.param);
            case INSERT_RECORD : return insertRecord(task.param);
            case REDUCE_RECORD : return reduceRecord(task.param);
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

    private Object insertRecord(Object record){
        FridgieDataSource ds =FridgieDataSource.getInstance(context);
        return ds.insertInventoryRecord((InventoryRecord) record);
    }

    private Object reduceRecord(Object record){
        InventoryRecord r = (InventoryRecord) record;
        r.setCount(r.getCount()-1);
        if(r.getCount() <= 0 ){
            deleteRecord(r);
            return null;
        }
        else{
            FridgieDataSource ds = FridgieDataSource.getInstance(context);
            ds.updateInventoryRecord(r);
            return r;
        }


    }


}
