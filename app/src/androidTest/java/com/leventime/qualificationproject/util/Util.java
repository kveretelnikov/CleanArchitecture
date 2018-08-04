package com.leventime.qualificationproject.util;

import timber.log.Timber;

/**
 * Contain methods for android testing
 *
 * @author kv
 */
public final class Util{

    private static final long DEFAULT_TIME_PAUSE_IN_MILLISECONDS = 500;

    /**
     * Pause test
     *
     * @param aMilliSeconds time of pause
     */
    public static void pauseTest(long aMilliSeconds){
        try{
            Thread.sleep(aMilliSeconds);
        } catch(InterruptedException aError){
            Timber.e(aError);
        }
    }

    /**
     * Pause test with default time
     */
    public static void pauseTest(){
        try{
            Thread.sleep(DEFAULT_TIME_PAUSE_IN_MILLISECONDS);
        } catch(InterruptedException aError){
            Timber.e(aError);
        }
    }
}
