package example.com.android_stopwatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class TimeTest
{

    private Time time;

    @Test
    public void getSeconds()
    {
        assertThat(time.getSeconds(50,10),not(1.00));
        assertThat(time.getSeconds(100,10),is(1.00));
    }

    @Test
    public void getMinutes()
    {
        assertNotEquals(1,time.getMinutes(5500,10));
        assertEquals(1,time.getMinutes(6000,10));
    }

    @Test
    public void getHours()
    {
        assertNotEquals(1,time.getHours(10000,10));
        assertEquals(1,time.getHours(360000,10));
    }

    @Before
    public void setUp() throws Exception
    {
        time=new Time();
    }

    @After
    public void tearDown() throws Exception
    {
        time=null;
    }
}