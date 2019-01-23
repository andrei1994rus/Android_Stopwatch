package example.com.android_stopwatch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

/**
 * Class of Main Activity.
 */
public class MainActivity extends AppCompatActivity
{
    /**
     * Object of  ImageButton which starts stopwatch.
     */
    ImageButton imButtonStart;

    /**
     * Object of ImageButton which resets time and laps.
     */
    ImageButton imButtonReset;

    /**
     * Object of ImageButton which outputs  time of current lap to  list view and does new lap.
     */
    ImageButton imButtonNewLap;

    /**
     * Object of ImageButton which pauses stopwatch.
     */
    ImageButton imButtonPause;

    /**
     * Object of TextView where is updated time of current lap.
     */
    TextView tvTime;

    /**
     * Object of ListView where is outputted time of every lap.
     */
    ListView listLaps;

    /**
     * Number of laps.
     */
    int laps=0;

    /**
     * Number of ticks.
     */
    long ticks=0;

    /**
     * Time interval (in milliseconds) of a tick.
     */
    int interval=10;

    /**
     * Object of class which is used for output
     */
    Time time=new Time();

    /**
     * Object of class TimeAsyncTask.
     */
    public TimeAsyncTask timeAsyncTask;

    /**
     * Object of class ArrayAdapter. It's used for fill of listLaps.
     */
    ArrayAdapter adapter;

    /**
     * Creates Activity.
     *
     * @param savedInstanceState
     *                          object of class Bundle. It's saved instance state of activity.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imButtonStart=(ImageButton) findViewById(R.id.imageButtonStart);

        imButtonReset=(ImageButton) findViewById(R.id.imageButtonReset);

        imButtonNewLap=(ImageButton) findViewById(R.id.imageButtonNewLap);

        imButtonPause=(ImageButton) findViewById(R.id.imageButtonPause);

        tvTime=(TextView) findViewById(R.id.Time_of_Lap);

        listLaps=(ListView) findViewById(R.id.list);

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        listLaps.setAdapter(adapter);

        imButtonReset.setEnabled(false);

        imButtonNewLap.setEnabled(false);
    }

    /**
     * Happens reaction to click on startImageButton. Starts  stopwatch.
     *
     * @param v
     *          object of class View which responds for event handling and is used for widgets.
     */
    public void onStartClick(View v)
    {
        timeAsyncTask=new TimeAsyncTask();
        timeAsyncTask.execute();

        imButtonStart.setVisibility(View.GONE);

        imButtonPause.setVisibility(View.VISIBLE);

        imButtonReset.setEnabled(false);

        imButtonNewLap.setEnabled(true);
    }

    /**
     * Happens  reaction to click on ResetImageButton. Resets time and laps.
     *
     * @param v
     *          object of class View which responds for event handling and is used for widgets.
     */
    public void onResetClick(View v)
    {
        tvTime.setText("00:00:00,00");

        adapter.clear();

        laps=0;

        ticks=0;

        imButtonPause.setVisibility(View.GONE);

        imButtonStart.setVisibility(View.VISIBLE);

        imButtonReset.setEnabled(false);

        imButtonNewLap.setEnabled(false);

    }

    /**
     * Happens reaction to click on PauseImageButton. Pauses stopwatch.
     *
     * @param v
     *          object of class View which responds for event handling and is used for widgets.
     */
    public void onPauseClick(View v)
    {
        imButtonPause.setVisibility(View.GONE);

        imButtonStart.setVisibility(View.VISIBLE);

        timeAsyncTask.cancel(true);

        imButtonReset.setEnabled(true);

        imButtonNewLap.setEnabled(false);
    }

    /**
     * Happens reaction to click on newLapImageButton.
     * Outputs time of current lap to list view and does new lap.
     *
     * @param v
     *          object of class View which responds for event handling and is used for widgets.
     */
    public void onNewLapClick(View v)
    {
        laps++;

        timeAsyncTask.cancel(true);

        double seconds=time.getSeconds(ticks,interval);

        long minutes=time.getMinutes(ticks,interval);

        long hours=time.getHours(ticks,interval);

        if (hours<10
                && minutes<10
                && seconds<10)
        {
            adapter.add(String.format("%d. 0%d:0%d:0%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours<10
                && minutes<10
                && seconds>=10)
        {
            adapter.add(String.format("%d. 0%d:0%d:%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours<10
                && minutes>=10
                && seconds>=10)
        {
            adapter.add(String.format("%d. 0%d:%d:%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours<10
                && minutes>=10
                && seconds<10)
        {
            adapter.add(String.format("%d. 0%d:%d:%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours>=10
                && minutes<10
                && seconds<10)
        {
            adapter.add(String.format("%d. %d:0%d:0%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours>=10
                && minutes<10
                && seconds>=10)
        {
            adapter.add(String.format("%d. %d:0%d:%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours>=10
                && minutes>=10
                && seconds<10)
        {
            adapter.add(String.format("%d. %d:%d:0%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        else if (hours>=10
                && minutes>=10
                && seconds>=10)
        {
            adapter.add(String.format("%d. %d:%d:%.2f",
                    laps,
                    hours,
                    minutes,
                    seconds));
        }

        ticks=0;

        timeAsyncTask=new TimeAsyncTask();
        timeAsyncTask.execute();
    }

    /**
     * Class which extends from AsyncTask. Is used for work of stopwatch.
     */
    public class TimeAsyncTask extends AsyncTask<Void, Long, Void>
    {

        /**
         * This method works in background (happen calculations).
         *
         * @param params
         *              input parameters.
         *
         * @return null (end of async task if canceled).
         */
        @Override
        protected Void doInBackground(Void... params)
        {
            while (true)
            {
                ticks++;

                publishProgress(ticks);

                SystemClock.sleep(interval);

                if (isCancelled())
                {
                    return null;
                }
            }
        }

        /**
         * Updates tvTime after calculations.
         * This method is performed after method publishProgress(values) (in method doInBackground(Void... params)).
         *
         * @param values
         *              saved values in method publishProgress(values)-intermediate parameter.
         */
        @Override
        protected void onProgressUpdate(Long... values)
        {
            super.onProgressUpdate(values);

            double seconds=time.getSeconds(values[0],interval);

            long minutes=time.getMinutes(values[0],interval);

            long hours=time.getHours(values[0],interval);

            if (hours<10
                    && minutes<10
                    && seconds<10)
            {
                tvTime.setText(String.format("0%d:0%d:0%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours<10
                    && minutes<10
                    && seconds>=10)
            {
                tvTime.setText(String.format("0%d:0%d:%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours<10
                    && minutes>=10
                    && seconds>=10)
            {
                tvTime.setText(String.format("0%d:%d:%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours<10
                    && minutes>=10
                    && seconds<10)
            {
                tvTime.setText(String.format("0%d:%d:0%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours>=10
                    && minutes<10
                    && seconds<10)
            {
                tvTime.setText(String.format("%d:0%d:0%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours>=10
                    && minutes<10
                    && seconds>=10)
            {
                tvTime.setText(String.format("%d:0%d:%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours>=10
                    && minutes>=10
                    && seconds<10)
            {
                tvTime.setText(String.format("%d:%d:0%.2f",
                        hours,
                        minutes,
                        seconds));
            }

            else if (hours>=10
                    && minutes>=10
                    && seconds>=10)
            {
                tvTime.setText(String.format("%d:%d:%.2f",
                        hours,
                        minutes,
                        seconds));
            }
        }

        /**
         * This method is performed before method doInBackground(Void... params)
         */
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        /**
         * This method is performed after method doInBackground(Void... params)
         *
         * @param aVoid
         *              output parameter.
         */
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
        }
    }
}
