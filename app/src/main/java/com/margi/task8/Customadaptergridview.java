package com.margi.task8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Margi on 14-Feb-17.
 */
public class Customadaptergridview extends BaseAdapter {

    Context context;
    ArrayList<Post> posts;
    LayoutInflater layoutInflater;

    public Customadaptergridview(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
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

    static class ViewHolder {
        TextView userid;
        TextView name;
    }

    private int[] tagCollection;

    @Override

    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.gridview, viewGroup, false);
            holder.userid = (TextView) view.findViewById(R.id.txtviw1);

            holder.name = (TextView) view.findViewById(R.id.txtviw2);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(posts.get(i).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(context, MainActivity2.class);
                a.putExtra("pos", posts.get(i).getId());
                context.startActivity(a);
            }
        });


        return view;
    }
}
