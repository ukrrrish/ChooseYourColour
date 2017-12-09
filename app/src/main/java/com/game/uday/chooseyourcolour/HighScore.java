package com.game.uday.chooseyourcolour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


public class HighScore extends Activity {

    ListView lv;
    ArrayList<String> score1=new ArrayList<String>();
    ArrayList<String> highscorelist;
    TextView highscoreheadline;
    ArrayList<ScorePOJO> scoreList;
    ScorePOJO scorePOJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        scoreList=new ArrayList<ScorePOJO>();
        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if(pref.getStringSet("key", null)!=null) {
            Iterator iter = pref.getStringSet("key", null).iterator();
            lv = (ListView) findViewById(R.id.listView123);
            score1 = new ArrayList<String>();
            while (iter.hasNext()) {
                
                scorePOJO=new ScorePOJO();
                // score1.add(iter.next().toString());
                String highScore[] = iter.next().toString().split(":");
                if (highScore[0] != null && highScore[1] != null) {

                    int scoreCheck = (int) (Float.valueOf(highScore[0]) * Float.valueOf(highScore[1]));
                    scorePOJO.setScore(Integer.parseInt(highScore[0]));
                    scorePOJO.setPercentile((int) (100 * Float.valueOf(highScore[1])));
                    scorePOJO.setColorselected(Integer.parseInt(highScore[2]));
                    scoreList.add(scorePOJO);

                }
            }
           // setScoreArraylist(score1);
            Collections.sort(scoreList,new ScorePOJO());
            lv.setAdapter(new CustomAdapterForHighScore(this, scoreList));

            highscoreheadline=(TextView)findViewById(R.id.highscoreheadline);
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "GreatVibes-Regular.otf");
            highscoreheadline.setTypeface(custom_font);
      // super.onCreate(savedInstanceState);
       // setContentView(R.layout.list);
       // lv=(ListView) findViewById(R.id.listView);
       // lv.setAdapter(new CustomAdapter(this));
        }
    }

    private void setScoreArraylist(ArrayList<String> score1){
        highscorelist=new ArrayList<String>();
       /* for()
        {

        }*/

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, StartGame.class);
// set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
