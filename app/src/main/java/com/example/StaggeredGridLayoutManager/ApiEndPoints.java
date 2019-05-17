package com.example.StaggeredGridLayoutManager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoints {

    @GET("search/photos")
    Call<Response> getAll(@Query("page") int page, @Query("query") String query,
                      @Query("client_id") String client_id,@Query("per_page") int per_page);

}
