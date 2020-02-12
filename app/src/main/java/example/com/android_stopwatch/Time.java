package example.com.android_stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * This class is used to output time.
 */
public class Time
{
    /**
     * Outputs seconds.
     *
     * @param value_t
     *               saved values in method publishProgress(values).
     *
     * @param interval
     *                time interval (in milliseconds) of tick.
     *
     * @return seconds.
     *
     */
    public final double getSeconds(long value_t,int interval)
    {
        return (value_t*interval)/1000.0%60;
    }

    /**
     * Outputs minutes.
     *
     * @param value_t
     *               saved values in method publishProgress(values).
     *
     * @param interval
     *                time interval (in milliseconds) of tick.
     *
     * @return minutes.
     *
     */
    public final long getMinutes(long value_t,int interval)
    {
        return TimeUnit.MILLISECONDS.toMinutes(value_t*interval)%60;
    }

    /**
     * Outputs hours.
     *
     * @param value_t
     *               saved values in method publishProgress(values).
     *
     * @param interval
     *                time interval (in milliseconds) of tick.
     *
     * @return hours.
     *
     */
    public final long getHours(long value_t,int interval)
    {
        return TimeUnit.MILLISECONDS.toHours(value_t*interval);
    }
}

