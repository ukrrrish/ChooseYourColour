package com.game.uday.chooseyourcolour;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{
    public static int  levelNo=6;
    public static int colorSelected;
    public static int colorCase=0;
    String [] result={"White","Red","Yellow","Green","Blue","Brown","Orange","Grey"};
    String [] caption={"EveryThing....","Love \u2665","hunger \u26A1","Nature \u26C5","Peace \u26F5","Earth \u2740","Cheerful \u263A","Rain \u2614"};
    Context context;
    int [] color={Color.argb(255, 255, 255, 255),Color.argb(255, 255, 0, 0),Color.argb(255, 255, 251, 59),Color.argb(255, 0, 255, 0),Color.argb(255, 0, 0, 255),Color.argb(255, 121, 85, 72),Color.argb(255, 255, 87, 34),Color.argb(255, 158, 158, 158),Color.argb(255, 0, 0, 0)};
    private static LayoutInflater inflater=null;
    public CustomAdapter(StartGame highScore) {
        context=highScore;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView tv1;
        RelativeLayout colorlistlayout;

    }
    @TargetApi(16)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.color_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.tv1=(TextView) rowView.findViewById(R.id.textView2);
        holder.colorlistlayout=(RelativeLayout)rowView.findViewById(R.id.colorlistlayout);
        holder.tv.setText(result[position]);
        holder.tv1.setText(caption[position]);
        switch (position) {
            case 1:
            holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.red));
                break;
            case 2:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.yellow));
                break;
            case 3:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.green));
                break;
            case 4:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.blue));
                break;
            case 5:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.brown));
                break;
            case 6:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.orange));
                break;
            case 7:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.grey));
                break;
            default:
                holder.colorlistlayout.setBackground(ContextCompat.getDrawable(context, R.drawable.white));
                break;
        }
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                colorCase=position;
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                colorSelected=color[position];
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.seek_bar);
                Window window = dialog.getWindow();
                Button OK_button=(Button) dialog.findViewById(R.id.okbutton);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                dialog.show();
                dialog.setCancelable(false);
                final SeekBar mSeekbar = (SeekBar) dialog.findViewById(R.id.seekBar1);
                OK_button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        TextView length_edit=(TextView)dialog.findViewById(R.id.textView2);
                        levelNo=(progress + 6);
                        length_edit.setText("Your Diffuculty level is :"+Integer.toString(progress + 6));
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
            }
        });
        return rowView;
    }

}