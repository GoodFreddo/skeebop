package main.java.tmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bennettl on 29/08/17.
 */
public class Scatman
    {
    String[] oneSyllable = {"Bee", "Bop", "Bo", "Bah", "Brim", "Brab", "Yah", "Du", "Ooh", "Shu", "Bu", "Sku", "Vu"};
    String[] twoSyllable = {"Beebop", "Doodah", "Bee-yo"};
    String[] threeSyllable = {"Skidilly", "Bidilly"};
    int beatHitNext = 0;
    int beatsPerLine = 0;
    int countRightNow = 0;
    BeatTimer beatTimer = null;

    public static void main(String[] args)
        {
        int[] beats = {0, 0};
        if (args.length != 2)
            {
            System.out.println("Please enter BPM and Beats per line");
            System.exit(1);
            }
        try
            {
            beats[0] = Integer.parseInt(args[0]);
            beats[1] = Integer.parseInt(args[1]);
            } catch (Exception e)
            {
            System.out.println("Please enter a valid interger value for beats BPM/Beats per line");
            System.exit(2);
            }
        if (beats[0] >= 1 && beats[1] >= 1)
            {
            Scatman scatman = new Scatman(beats);
            }
        }

    public Scatman(int[] beats)
        {
        boolean isLoopDesired = true;
        int beatsPerMinute = beats[0];
        this.beatsPerLine = beats[1];
        beatTimer = new BeatTimer(beatsPerMinute, beatsPerLine);
        try
            {
            beatTimer.run();
            } catch (IllegalThreadStateException e)
            {
            }
        while (isLoopDesired)
            {
            countRightNow = beatTimer.count;
            if (countRightNow != beatHitNext)
                {
//                beatHitNext = beatTimer.count;
                try
                    {
                    Thread.sleep(5);
                    } catch (InterruptedException e)
                    {
                    e.printStackTrace();
                    }
//                while (beatHitNext >= beatsPerLine)
//                    {
//                    countRightNow = beatTimer.count;
//                    if (countRightNow == 0)
//                        {
//                        beatHitNext = 0;
//                        }
//                    else
//                        {
//                        try
//                            {
//                            Thread.sleep(50);
//                            } catch (InterruptedException e)
//                            {
//                            e.printStackTrace();
//                            }
//                        }
//                    }
                }
            else
                {
//                beatHitNext = beatTimer.count;
                if (beatsPerLine - beatHitNext > 3)
                    {
                    printAWord(3);
                    }
                else
                    {
                    switch (beatsPerLine - beatHitNext)
                        {
                        case 0:
                        case 1:
                            printAWord(1);
                            break;
                        case 2:
                            printAWord(2);
                            break;
                        case 3:
                            printAWord(3);
                            break;
                        }
                    }
                }
            }
        }

    private void printAWord(int wordMaxLength)
        {
        List<String[]> dictionaries = new ArrayList<>();
        Random random = new Random();
        int dictionary = 0;
        int numberWeighter = random.nextInt(9);
        switch (wordMaxLength)
            {
            case 1:
                dictionaries.add(0, oneSyllable);
                break;
            case 2:
                dictionaries.add(0, oneSyllable);
                dictionaries.add(1, twoSyllable);
                if (numberWeighter > 6)
                    {
                    dictionary = 1;
                    }
                break;
            case 3:
                dictionaries.add(0, oneSyllable);
                dictionaries.add(1, twoSyllable);
                dictionaries.add(2, threeSyllable);
                if (numberWeighter > 7)
                    {
                    dictionary = 2;
                    }
                else if (numberWeighter > 5)
                    {
                    dictionary = 1;
                    }
                break;
            }
        String word = dictionaries.get(dictionary)[random.nextInt(dictionaries.get(dictionary).length)];
        System.out.print(word + " ");
        beatHitNext = countRightNow + dictionary + 1;
        if (beatHitNext >= beatsPerLine)
            {
            beatHitNext = 0;
            }
        }
    }
