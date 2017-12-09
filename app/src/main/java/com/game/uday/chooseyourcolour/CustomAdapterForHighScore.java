package com.game.uday.chooseyourcolour;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterForHighScore extends BaseAdapter{
   List<ScorePOJO> result=new ArrayList<ScorePOJO>();
    String [] caption={"EveryThing....","Love \u2665","hunger \u26A1","Nature \u26C5","Peace \u26F5","Earth \u2740","Cheerful \u263A","Rain \u2614"};
    Context context;
    int [] color={Color.argb(255, 255, 255, 255),Color.argb(255, 255, 0, 0),Color.argb(255, 0, 255, 0),Color.argb(255, 0, 0, 255),Color.argb(255, 0, 255, 255),Color.argb(255, 255, 0, 255),Color.argb(255, 255, 255, 0),Color.argb(255, 255, 255, 255),Color.argb(255, 0, 0, 0)};
    private static LayoutInflater inflater=null;
    public CustomAdapterForHighScore(HighScore highScore,ArrayList<ScorePOJO> result) {
        context=highScore;
        this.result=result;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.size();
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
        TextView serial_number,score,percentile,consolidatedscore;
        RelativeLayout main_layout;
    }
    @TargetApi(16)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.highscore_list, null);

        holder.main_layout=(RelativeLayout) rowView.findViewById(R.id.main_layout);

        switch (result.get(position).getColorselected()) {
            case 1:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.red));
                break;
            case 2:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.yellow));
                break;
            case 3:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.green));
                break;
            case 4:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.blue));
                break;
            case 5:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.brown));
                break;
            case 6:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.orange));
                break;
            case 7:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.grey));
                break;
            default:
                holder.main_layout.setBackground(ContextCompat.getDrawable(context, R.drawable.white));
                break;
        }

        holder.serial_number=(TextView) rowView.findViewById(R.id.serial_Number);
        holder.serial_number.setText(Integer.toString(position+1));

        holder.score=(TextView) rowView.findViewById(R.id.score);
        holder.score.setText(Integer.toString(result.get(position).getScore()));

        holder.percentile=(TextView) rowView.findViewById(R.id.percentile);
        holder.percentile.setText(Integer.toString(result.get(position).getPercentile()));

        holder.consolidatedscore=(TextView) rowView.findViewById(R.id.consolidated_score);
        holder.consolidatedscore.setText(Integer.toString(result.get(position).getOverAllScore()));

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return rowView;
    }

}