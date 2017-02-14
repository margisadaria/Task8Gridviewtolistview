package com.margi.task8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Margi on 14-Feb-17.
 */
public class Mainactivitylist extends AppCompatActivity
{

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        TextView textView;

        textView=(TextView)findViewById(R.id.txtvw6);

        String quotes;
        Intent intent=getIntent();
        quotes=intent.getStringExtra("pos");

        textView.setText(quotes);


        Button btn;
        btn=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

    }
}

