package com.example.macstudio.photoappjava.networking;

import com.example.macstudio.photoappjava.networking.models.PhotoAppDataResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PhotoServiceClient {
    @GET("/Prod/users/self/media/recent")
    Call<PhotoAppDataResponse> photoForUser(@Header("Authorization") String authorization);

    @POST("/Prod/media/{media_id}/likes")
    Call<ResponseBody> likePhoto(@Header("Authorization") String authorization, @Path("media_id") String media_id);

    @DELETE("/Prod/media/{media_id}/likes")
    Call<ResponseBody> unlikePhoto(@Header("Authorization") String authorization, @Path("media_id") String media_id);
}
