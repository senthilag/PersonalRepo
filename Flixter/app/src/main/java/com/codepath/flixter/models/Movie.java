package com.codepath.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by senthilg on 1/23/17.
 */

public class Movie {
    String posterPath;
    String orginalTitle;
    String overView;
    String rating;
    String backdropPath;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.orginalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.rating = jsonObject.getString("vote_average");
        this.backdropPath = jsonObject.getString("backdrop_path");
    }

    public static ArrayList<Movie> getJsonAr(JSONArray jsonArray) {
        ArrayList<Movie> results = new ArrayList<Movie>();

        for(int i=0; i<jsonArray.length();i++) {
            try {
                results.add(new Movie(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getOrginalTitle() {
        return orginalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public int getRating() {
        if ((Double.parseDouble(rating)) > 6.0) {
            return 1;
        } else {
            return 0;
        }
    }
}
