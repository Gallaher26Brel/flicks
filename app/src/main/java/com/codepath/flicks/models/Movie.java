package com.codepath.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackDrop() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backDrop);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public int getRating() {
        return rating;
    }

    String posterPath;
    String backDrop;
    String originalTitle;
    String overview;
    int rating;

    public int getId() {
        return id;
    }

    int id;

    public Movie (JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backDrop = jsonObject.getString("backdrop_path");
        this.rating = jsonObject.getInt("vote_average");
        this.id = jsonObject.getInt("id");
    }

    public static ArrayList<Movie> fromJSONArray (JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
