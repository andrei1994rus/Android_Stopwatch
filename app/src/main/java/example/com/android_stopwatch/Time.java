package example.com.android_stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Class is used for outputting time.
 */
public class Time
{
    /**
     * Outputs seconds.
     *
     * @return seconds.
     */
    public final double getSeconds(long value_t,int interval)
    {
        double seconds=(value_t*interval)/1000.0%60;
        return seconds;
    }

    /**
     * Outputs minutes.
     *
     * @return minutes.
     */
    public final long getMinutes(long value_t,int interval)
    {
        long minutes=TimeUnit.MILLISECONDS.toMinutes(value_t*interval)%60;
        return minutes;
    }

    /**
     * Outputs hours.
     *
     * @return hours.
     */
    public final long getHours(long value_t,int interval)
    {
        long hours=TimeUnit.MILLISECONDS.toHours(value_t*interval);
        return hours;
    }
}

