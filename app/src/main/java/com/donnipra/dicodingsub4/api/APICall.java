package com.donnipra.dicodingsub4.api;

/*
 * Created by donni.
 * Last modified 6/12/18 11:54 AM.
 */

import com.donnipra.dicodingsub4.model.DetailModel;
import com.donnipra.dicodingsub4.model.NowPlayingModel;
import com.donnipra.dicodingsub4.model.SearchModel;
import com.donnipra.dicodingsub4.model.UpcomingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APICall {

    @GET("movie/now_playing")
    Call<NowPlayingModel> getNowPlayingMovie(@Query("language") String language);

    @GET("movie/{movie_id}")
    Call<DetailModel> getDetailMovie(@Path("movie_id") String movie_id, @Query("language") String language);

    @GET("movie/upcoming")
    Call<UpcomingModel> getUpcomingMovie(@Query("language") String language);

    @GET("search/movie")
    Call<SearchModel> getSearchMovie(@Query("query") String query, @Query("language") String language);
}
