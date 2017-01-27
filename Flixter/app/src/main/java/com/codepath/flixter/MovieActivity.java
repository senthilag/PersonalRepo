package com.codepath.flixter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.flixter.adapters.MovieAdapter;
import com.codepath.flixter.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MovieActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    MovieAdapter movieAdapter = null;
    ListView lsView = null;
    ArrayList<Movie> movies = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        fetchTimelineAsync(0);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync(0)
                ;            }
        });
        // Configure the refreshing colors

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);
        setupclickListener();
    }

    private void setupclickListener() {
        lsView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                        Intent i = new Intent(getApplicationContext(), ExposeDetails.class);

                        i.putExtra("title",movies.get(pos).getOrginalTitle());
                        i.putExtra("overview",movies.get(pos).getOverView());
                        i.putExtra("vote_average",movies.get(pos).getVoteRating());
                        i.putExtra("backdrop_path",movies.get(pos).getBackdropPath());
                        startActivity(i);
                    }
                }
        );
    }

    public void fetchTimelineAsync(int page) {

        movies = new ArrayList<>();
        lsView = (ListView) findViewById(R.id.lvMovies);
        movieAdapter = new MovieAdapter(this,movies);
        lsView.setAdapter(movieAdapter);
        final String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray mvResult = null;
                try {
                    mvResult = response.getJSONArray("results");
                    Log.d("Senthil response is :",mvResult.toString());
                    movies.addAll(Movie.getJsonAr(mvResult));
                    Log.d("Senthil response is :",movies.toString());
                    movieAdapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
