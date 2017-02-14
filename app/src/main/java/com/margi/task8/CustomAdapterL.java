package com.margi.task8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Margi on 14-Feb-17.
 */
public class CustomAdapterL extends BaseAdapter {
    Context context;
    ArrayList<Quotes> posts;
    LayoutInflater layoutInflater;

    public CustomAdapterL(Context context, ArrayList<Quotes> posts)
    {
        this.context=context;
        this.posts=posts;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder
    {
        TextView id;
        TextView cat_id;
        TextView quotes;


    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder;


        if(view==null) {
            holder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.listview, viewGroup, false);
            holder.id = (TextView) view.findViewById(R.id.textvw3);
            holder.cat_id = (TextView) view.findViewById(R.id.textvw4);
            holder.quotes=(TextView)view.findViewById(R.id.textvw5);

            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)view.getTag();
        }
        holder.quotes.setText("Quotes: "+posts.get(i).getQuotes());

        holder.quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Mainactivitylist.class);
                intent.putExtra("pos", posts.get(i).getQuotes());
                context.startActivity(intent);
            }
        });



        return view;

    }
}
