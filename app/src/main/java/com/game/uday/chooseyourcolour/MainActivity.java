package com.game.uday.chooseyourcolour;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;


@SuppressWarnings("ALL")
public class MainActivity extends Activity implements View.OnTouchListener, View.OnClickListener {
    static int width;
    static int height;
    static int score = 0;
    List<LinearLayout> linearLayoutList;
    Random rand;
    static int numberOfLayouts;
    boolean isLayoutStylehorozontal = true;
    RelativeLayout parentRealativeLayout;
    Random rnd = new Random();
    Button buttonNo, buttonStart;
    Dialog dialog;
    int IDselected;
    ProgressBar status;
    CountDownTimer mCountDownTimer;
    private int i=0;
    TextView dialog_percentile;
    TextView testView;
    private int textSize=18;
    int textflag=0;
    int rotationX=0;
    int rotationY=0;
    int levelselected=6;
    int levelflag=0;
    private boolean fastMode=false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
  //  {
   /*     super.onCreate(savedInstanceState);
        setContentView(R.layout.color_list);*/

      //  SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
      //  Iterator iter = pref.getStringSet("key", null).iterator();
       // while (iter.hasNext()) {
            //RelativeLayout item = (RelativeLayout)findViewById(R.id.highscore_layout);
           // View child = getLayoutInflater().inflate(R.layout.highscore_list, null);
            //item.addView(child);
      //  }
   // }
   {
       fastMode=false;
        super.onCreate(savedInstanceState);
       score=0;
       levelselected=CustomAdapter.levelNo;
       levelflag=0;
        setContentView(R.layout.activity_main);
        status = (ProgressBar) findViewById(R.id.status);
        parentRealativeLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        height = (int) getApplicationContext().getResources().getDisplayMetrics().heightPixels - 50;
        width = (int) getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        linearLayoutList = new ArrayList<>();
        linearLayoutList.add(new LinearLayout(this));
        rand = new Random();
        Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
       // linearLayoutList.get(0).setBackgroundColor(Color.BLACK);
       linearLayoutList.get(0).setBackgroundColor(CustomAdapter.colorSelected);
        IDselected = 1;
        linearLayoutList.get(0).setDrawingCacheBackgroundColor(Color.BLACK);
        linearLayoutList.get(0).setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams LLParams = new ViewGroup.LayoutParams((int) getApplicationContext().getResources().getDisplayMetrics().widthPixels , (int) getApplicationContext().getResources().getDisplayMetrics().heightPixels);
        linearLayoutList.get(0).setLayoutParams(LLParams);
        linearLayoutList.get(0).setId(1);
        linearLayoutList.get(0).setOnTouchListener(this);
        parentRealativeLayout.addView(linearLayoutList.get(0));
       testView = new TextView(this);
       testView.setText("Touch The Screen to Continue");
       testView.setTextSize(18);
       testView.setTextColor(Color.BLACK);
       RelativeLayout.LayoutParams textViewParams =
               new RelativeLayout.LayoutParams(
                       RelativeLayout.LayoutParams.WRAP_CONTENT,
                       RelativeLayout.LayoutParams.WRAP_CONTENT);
       textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
       parentRealativeLayout.addView(testView, textViewParams);
        numberOfLayouts = 1;
    }

    @Override
    public void onStart() {
        super.onRestart();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            status.setProgress(0);
        }
        super.onBackPressed();
    }

    float percentile;
    float display;
    @Override
    public boolean onTouch(View v, MotionEvent event)
     {
        if(v.getId()==IDselected)
        {
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
                status.setProgress(0);
            }
            score = score + v.getId() + 1;
            percentile = numberOfLayouts * (numberOfLayouts + 1) / 2;
            display = (score - 1) / percentile;
            levelflag++;
            if((levelflag<=levelselected)&& !fastMode) {
                dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setTitle("Dialog Title");
                dialog.setContentView(R.layout.custom_dialog);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                TextView title = (TextView) dialog.findViewById(R.id.title);
                TextView level_card = (TextView) dialog.findViewById(R.id.level_card);
                if(levelflag==levelselected)
                {
                    level_card.setText("Comming up last level\nYou level is : "+Integer.toString(levelflag)+"\nYour current score is : "+Integer.toString((score - 1)));
                    level_card.setTextColor(Color.BLUE);
                }
                else {
                    level_card.setText("level : " + Integer.toString(levelflag) + "\nYour current score is : " + Integer.toString((score - 1)));
                }
                title.setText("Total points secured is : " +Integer.toString((score-1)*(int)(display*100)));
                buttonNo = (Button) dialog.findViewById(R.id.dontcare);
                buttonStart = (Button) dialog.findViewById(R.id.dismiss);
                dialog_percentile = (TextView) dialog.findViewById(R.id.dialog_percentile);
                dialog_percentile.setText(Integer.toString((int) (display * 100)) + "%");
                buttonNo.setOnClickListener(this);
                buttonStart.setOnClickListener(this);
                View view = dialog.findViewById(R.id.percentage);
                View view1 = dialog.findViewById(R.id.percentagebackground);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                params.height = (int) (600 * display);
                if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
                {
                    buttonNo.setTextColor(Color.BLACK);
                    buttonStart.setTextColor(Color.BLACK);
                }
                view.setLayoutParams(params);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.setCancelable(false);
                return false;
            }
            else if(levelflag>levelselected)
            {
                if( setHighScore())
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Game Over");
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setMessage(("Selected Level reached\nCongrats \u265A \n Your score " + Integer.toString((score - 1) * (int) (display * 100)))+" made to top five" );
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent positveActivity = new Intent(getApplicationContext(),
                                    HighScore.class);
                            positveActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(positveActivity);
                        }
                    });
                          /* alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   dialog.cancel();
                               }
                           });*/
                    // set neutral button: Exit the app message
                           /*alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   MainActivity.this.finish();
                               }
                           });*/

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("GameOver");
                    alertDialogBuilder.setMessage("Selected Level reached.\n Your Final Score is : " +Integer.toString((score - 1) * (int) (display * 100)));
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Intent positveActivity = new Intent(getApplicationContext(),
                                    StartGame.class);
                            positveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(positveActivity);
                        }
                    });
                    alertDialogBuilder.setNegativeButton("ReTry",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            finish();
                        }
                    });
                    // set neutral button: Exit the app message
                           /*alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   MainActivity.this.finish();
                               }
                           });*/

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
            else if(fastMode)
            {
                creatLayout(v);
            }

        }
        return false;

    }


    @Override
    public void onClick(View v) /*{
        if(v.getId()!=R.id.dontcare) {
           *//* dialog.dismiss();
            Intent intent = new Intent(this, HighScore.class);
            startActivity(intent);
            finish();*//*
            dialog.dismiss();
            creatLayout(v);
        }
        else {
            SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
       if(pref.getStringSet("key", null)==null) {
        Set<String> set = new HashSet<String>();
           set.add(String.valueOf(score)+":"+String.valueOf(percentile));
           edit.putStringSet("key", set);
           edit.clear();
           edit.commit();
           TextView title = (TextView) dialog.findViewById(R.id.title);
           title.setText(pref.getStringSet("key", null).toString());
       }else {
        Set<String> set =pref.getStringSet("key", null);
            set.add(String.valueOf(score) + ":" + String.valueOf(percentile));
           edit.putStringSet("key", set);
           edit.clear();
           edit.commit();
           TextView title = (TextView) dialog.findViewById(R.id.title);
           title.setText(pref.getStringSet("key", null).toString());
           if( checkHighScore()){
               title.setText("true");
           }
           else{
               title.setText("False");
           }
        }
if(pref.getStringSet("key", null).size()<=5)
{
    Set<String> set =pref.getStringSet("key", null);
    set.add(String.valueOf(score) + ":" + String.valueOf(percentile));
    edit.putStringSet("key", set);
    edit.clear();
    edit.commit();
    checkHighScore();
}

        }
        {
           *//* SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);

            SharedPreferences.Editor edit = pref.edit();
            ScorePOJO details=null;
            if(pref.getStringSet("score", null)==null) {
                Set<String> set = new HashSet<String>();
                Gson gson = new Gson();
                details=new ScorePOJO();
                details.setScore(score);
                details.setPercentile(percentile);
                String json = gson.toJson(details);
                set.add(json);
                edit.putStringSet("score", set);
                edit.clear();
                edit.commit();
                TextView title = (TextView) dialog.findViewById(R.id.title);
                title.setText(pref.getStringSet("score", null).toString());
            }else {
                Set<String> set =pref.getStringSet("score", null);
                Gson gson = new Gson();
                details=new ScorePOJO();
                details.setScore(score);
                details.setPercentile(percentile);
                String json = gson.toJson(details);
                set.add(json);
                edit.clear();
                edit.commit();

                TextView title = (TextView) dialog.findViewById(R.id.title);

                title.setText(pref.getStringSet("score", null).toString());
            }*//*
        }
    }*/
    {

        if(v.getId()==R.id.dismiss){
            fastMode=true;
            dialog.dismiss();
            creatLayout(v);

        }
        else{

            dialog.dismiss();
            creatLayout(v);
        }
    }

    private boolean checkHighScore()
    {
        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        Iterator iter = pref.getStringSet("key", null).iterator();
        while (iter.hasNext()) {
           String highScore[]= iter.next().toString().split(":");
            if(highScore[0]!=null && highScore[1]!=null)
            {
             int scoreCheck =(int)(Float.valueOf(highScore[0])*Float.valueOf(highScore[1]));
                if(scoreCheck<=(int)((score-1)*display))
                {
                    return true;
                }
            }
        }
    return false;
    }

    private boolean setHighScore()
    {
        Set<String> set = new HashSet<String>();
        int count=0;
        Object highScoreObject=null;
        int highScoreCheck=999;
        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(pref.getStringSet("key", null)==null){

                set.add(String.valueOf(score - 1) + ":" + String.valueOf(display) + ":" + String.valueOf(CustomAdapter.colorCase));
                edit.putStringSet("key", set);
                edit.clear();
                edit.commit();
            return true;
        }
        Iterator iter = pref.getStringSet("key", null).iterator();

            if(pref.getStringSet("key", null).size()<5){

                set= pref.getStringSet("key", null);//.add(String.valueOf(score-1) + ":" + String.valueOf(display)+":"+String.valueOf(CustomAdapter.colorCase));
                set.add(String.valueOf(score - 1) + ":" + String.valueOf(display) + ":" + String.valueOf(CustomAdapter.colorCase));
                edit.putStringSet("key", set);
                edit.clear();
                edit.commit();
                return  true;
            }
        else if(checkHighScore()){

        while (iter.hasNext()) {
            Object tempSetObject = iter.next();
            String highScore[] = tempSetObject.toString().split(":");
            if (highScore[0] != null && highScore[1] != null) {
                count++;
                int scoreCheck = (int) (Float.valueOf(highScore[0]) * Float.valueOf(highScore[1]));
                if (scoreCheck <= (int) highScoreCheck) {
                        highScoreCheck = scoreCheck;
                        highScoreObject = tempSetObject;
                }
            }
        }
        }
        else {
                return false;
            }

       set= pref.getStringSet("key", null);//.remove(highScoreObject);
        set.remove(highScoreObject);
       // pref.getStringSet("key", null).add(String.valueOf(score-1) + ":" + String.valueOf(display)+":"+String.valueOf(CustomAdapter.colorCase));
        set.add(String.valueOf(score - 1) + ":" + String.valueOf(display) + ":" + String.valueOf(CustomAdapter.colorCase));
        edit.putStringSet("key", set);
        edit.clear();
        edit.commit();
        return true;
    }

    public void creatLayout(View v) {
        textSize=18;
        textflag=0;

        height = (int) getApplicationContext().getResources().getDisplayMetrics().heightPixels - 3;
        width = (int) getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        numberOfLayouts++;
           /* System.out.println("------" + numberOfLayouts + "-------" + linearLayoutList.size() + "------" + v.getId());*/
        View view;
        for (int k = 0; k < linearLayoutList.size(); k++) {
            parentRealativeLayout.removeView(linearLayoutList.get(k));
        }
        linearLayoutList.clear();
        if (rnd.nextInt(2) == 0) {
            isLayoutStylehorozontal = false;
        }
        for (int i = 0; i < (numberOfLayouts - 1); i++) {
            if (isLayoutStylehorozontal) {
                linearLayoutList.add(new LinearLayout(this));
                linearLayoutList.get(i).setOrientation(LinearLayout.HORIZONTAL);
                view = new View(this);
                width = width / 2;
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        (int) width,
                        (int) height));
                view.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                view.setOnTouchListener(this);
                view.setId(i);
                linearLayoutList.get(i).addView(view);
                // linearLayoutList.get(i).setId(i);
                isLayoutStylehorozontal = false;
            } else {
                linearLayoutList.add(new LinearLayout(this));
                linearLayoutList.get(i).setOrientation(LinearLayout.VERTICAL);
                view = new View(this);
                height = height / 2;
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        (int) width,
                        (int) height));
                view.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                view.setOnTouchListener(this);
                view.setId(i);
                linearLayoutList.get(i).addView(view);
                //  linearLayoutList.get(i).setId(i);
                isLayoutStylehorozontal = true;
            }
        }
        view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(
                (int) width,
                (int) height));
        view.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        view.setId((linearLayoutList.size()));
        view.setOnTouchListener(this);
        linearLayoutList.get((linearLayoutList.size() - 1)).addView(view);

        for (int j = (linearLayoutList.size() - 1); j >= 1; j--) {
            linearLayoutList.get(j - 1).addView(linearLayoutList.get(j));
        }
        parentRealativeLayout.addView(linearLayoutList.get(0));
        parentRealativeLayout.setClickable(true);
        IDselected = rnd.nextInt(numberOfLayouts);
        View needed = findViewById(IDselected);
      //  needed.setBackgroundColor(Color.BLACK);
        needed.setBackgroundColor(CustomAdapter.colorSelected);


        switch (rnd.nextInt(4)) {
            case 0:
                parentRealativeLayout.setRotation(180);
                parentRealativeLayout.setRotationY(180);
                rotationX=180;
                rotationY=180;
                break;
            case 1:
                parentRealativeLayout.setRotation(180);
                parentRealativeLayout.setRotationY(360);
                rotationX=180;
                rotationY=0;

                break;
            case 2:
                parentRealativeLayout.setRotation(360);
                parentRealativeLayout.setRotationY(180);
                rotationX=0;
                rotationY=180;
                break;
            case 3:
                parentRealativeLayout.setRotation(360);
                parentRealativeLayout.setRotationY(360);
                rotationX=0;
                rotationY=0;
                break;
            default:
                break;
        }
        i=0;



        status.setProgress(i);
        testView = new TextView(this);

                testView.setTextSize(18);
        testView.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams textViewParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        parentRealativeLayout.addView(testView, textViewParams);

            mCountDownTimer = new CountDownTimer(2200, 200) {

                @Override
                public void onTick(long millisUntilFinished) {
                    i+=10;
                    status.setProgress(i);
                    textSize=textSize+3;
                    testView.setTextSize(textSize);
                    textflag=1+textflag;
                    switch (textflag) {
                        case 0:
                            testView.setText("\u2788");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 1:
                            testView.setText("\u2787");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 2:
                            testView.setText("\u2786");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 3:
                            testView.setText("\u2785");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 4:
                            testView.setText("\u2784");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 5:
                            testView.setText("\u2783");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 6:
                            testView.setText("\u2782");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 7:
                            testView.setText("\u2781");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 8:
                            testView.setText("\u2780");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;
                        case 10:
                            testView.setText("Ohh..!.Time Up");
                            testView.setRotation(rotationX);
                            testView.setRotationY(rotationY);
                            break;

                        default:
                            break;

                    }
                    if(i>=100)
                    {
                       if( setHighScore())
                       {
                           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                           alertDialogBuilder.setTitle("TimeUp \u231B");
                           alertDialogBuilder.setMessage("Welldone\u2600 your score " + Integer.toString((score - 1) * (int) (display * 100)) + " made to Top five");
                           alertDialogBuilder.setCancelable(false);
                           alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                                   Intent positveActivity = new Intent(getApplicationContext(),
                                           HighScore.class);
                                   positveActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                   startActivity(positveActivity);
                               }
                           });
                          /* alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   dialog.cancel();
                               }
                           });*/
                           // set neutral button: Exit the app message
                           /*alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   MainActivity.this.finish();
                               }
                           });*/

                           AlertDialog alertDialog = alertDialogBuilder.create();
                           alertDialog.show();
                       }
                        else
                       {
                           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                           alertDialogBuilder.setCancelable(false);
                           alertDialogBuilder.setTitle("Time Up \u231B");
                           alertDialogBuilder.setMessage("Your Final score : " + Integer.toString((score - 1) * (int) (display * 100)));
                           alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                                   Intent positveActivity = new Intent(getApplicationContext(),
                                           StartGame.class);
                                   positveActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                   startActivity(positveActivity);
                               }
                           });
                           alertDialogBuilder.setNegativeButton("ReTry",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   finish();
                               }
                           });
                           // set neutral button: Exit the app message
                           /*alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog,int id) {
                                   MainActivity.this.finish();
                               }
                           });*/

                           AlertDialog alertDialog = alertDialogBuilder.create();
                           alertDialog.show();
                       }
                    }

                    //finish();
                }
                @Override
                public void onFinish() {
                    //Do what you want
                    i++;
                    status.setProgress(0);
                }
            };
            mCountDownTimer.start();
        }
    //private ProgressDialog progress;

}



