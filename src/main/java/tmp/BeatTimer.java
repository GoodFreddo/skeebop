package main.java.tmp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bennettl on 29/08/17.
 */
public class BeatTimer implements Runnable
    {
    int count = 0;
    int beatsPerLine = 0;
    int bps = 0;
    Timer timer = new Timer();

    public BeatTimer(int bpm, int beatsPerLine)
        {
        this.beatsPerLine = beatsPerLine;
        this.bps = bpm/60;
        }

    TimerTask countUp = new TimerTask()
        {

        @Override
        public void run()
            {

            if (count == beatsPerLine -1)
                {
                System.out.println();
                count = 0;
                }
            else
                {
                count++;
                }
            }
        };

    @Override
    public void run()
        {
        timer.scheduleAtFixedRate(countUp, 0, 1000/bps);
        }
    }
