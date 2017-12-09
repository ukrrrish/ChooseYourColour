package com.game.uday.chooseyourcolour;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StartGame extends Activity implements View.OnClickListener{
    private Dialog dialog;
    private Button rules,highscore,startgame;
    private TextView gameName,listViewTitle;
    static int id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        id=R.layout.start;
        rules=(Button)findViewById(R.id.rules);
        highscore=(Button)findViewById(R.id.highscore);
        startgame=(Button)findViewById(R.id.startgame);
        gameName=(TextView)findViewById(R.id.game_name);
        if(android.os.Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP)
        {
            rules.setTextColor(Color.BLACK);
            highscore.setTextColor(Color.BLACK);
            startgame.setTextColor(Color.BLACK);
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "GreatVibes-Regular.otf");
        gameName.setTypeface(custom_font);
        rules.setOnClickListener(this);
        highscore.setOnClickListener(this);
        startgame.setOnClickListener(this);
    }
    @Override
    public void onClick(View buttonEvent) {


    if(buttonEvent.getId()==R.id.rules)
    {
        dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        RelativeLayout dialogLayout = new RelativeLayout(this);
        dialogLayout.setPadding(14, 14, 14, 14);
        dialogLayout.setBackgroundColor(Color.WHITE);
        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams textViewParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        textViewParams.addRule(RelativeLayout.CENTER_VERTICAL);

        Button myButton = new Button(this);
        myButton.setText("Back");
        myButton.setBackgroundColor(Color.BLUE);
        myButton.setTextColor(Color.WHITE);

        TextView testView = new TextView(this);
        testView.setText("Prior to playing the game you need to pick a color from the list, then you need to select the level up to which you want to continue.\n\n"+
                         "How to Play.\n Once the game begins entire screen is divided into sections. In which each section is filled with different color and a counter is displayed in the middle. All you need to do is to touch the color which you had selected, before counter reaches zero to pass each level.\n\n"+
                         "Score criteria.\n As you move from one level to another number of sections increases size of the selected section varies randomly from one level to another, point scored at a level is dependent on the chosen color section lesser the size of the section more will be the points scored.");
                testView.setTextSize(16);
        testView.setTextColor(Color.BLACK);
        dialogLayout.addView(myButton, buttonParams);
        dialogLayout.addView(testView, textViewParams);
        dialog.setContentView(dialogLayout);
        dialog.show();
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    else if(buttonEvent.getId()==R.id.highscore)
    {
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
    }

    else if(buttonEvent.getId()==R.id.startgame)
    {
        ListView lv;
        setContentView(R.layout.list);
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this));
        listViewTitle=(TextView)findViewById(R.id.game_title);
        id=R.id.game_title;
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "GreatVibes-Regular.otf");
        listViewTitle.setTypeface(custom_font);
    }
    }

    @Override
    public void onBackPressed() {
        if(id==R.id.game_title) {
            setContentView(R.layout.start);
            id=R.layout.start;
            rules=(Button)findViewById(R.id.rules);
            highscore=(Button)findViewById(R.id.highscore);
            startgame=(Button)findViewById(R.id.startgame);
            gameName=(TextView)findViewById(R.id.game_name);
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "GreatVibes-Regular.otf");
            gameName.setTypeface(custom_font);
            rules.setOnClickListener(this);
            highscore.setOnClickListener(this);
            startgame.setOnClickListener(this);
        }
        else
        {
            finish();
        }
        return;
    }
}
