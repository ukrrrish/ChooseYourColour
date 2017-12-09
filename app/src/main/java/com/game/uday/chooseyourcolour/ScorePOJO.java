package com.game.uday.chooseyourcolour;

import java.util.Comparator;

/**
 * Created by Ud@y on 09-01-2016.
 */
public class ScorePOJO implements Comparator<ScorePOJO> {
    private int score;
    private int percentile;

    public int getColorselected() {
        return colorselected;
    }

    public void setColorselected(int colorselected) {
        this.colorselected = colorselected;
    }

    private int colorselected;

    public void setScore(int score) {
        this.score = score;
    }

    public void setPercentile(int percentile) {
        this.percentile = percentile;
    }

    public int getScore() {
        return score;
    }

    public int getPercentile() {
        return percentile;
    }

    public int getOverAllScore()
    {
        return percentile*score;
    }

    @Override
    public int compare(ScorePOJO o1, ScorePOJO o2) {
        //return o1.getOverAllScore().compareTo(o2.getOverAllScore());
        if(o1.getOverAllScore()>o2.getOverAllScore())
            return -1;
        else if(o1.getOverAllScore()<o2.getOverAllScore())
            return 1;

        return 0;

    }
}
