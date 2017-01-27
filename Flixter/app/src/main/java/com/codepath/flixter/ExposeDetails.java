package com.codepath.flixter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by senthilg on 1/26/17.
 */

public class ExposeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expose_details);


        String title = getIntent().getStringExtra("title");
        TextView textView = (TextView) findViewById(R.id.mvTitle);
        textView.setText(title);

        String backdrop_path = getIntent().getStringExtra("backdrop_path");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(getApplicationContext()).load(backdrop_path).into(imageView);

        String overview = getIntent().getStringExtra("overview");
        TextView textView1 = (TextView) findViewById(R.id.movieOverview);
        textView1.setText(overview);

    }
}
