package com.eshraq.gosport.webservice;

import com.eshraq.gosport.model.Article;
import com.eshraq.gosport.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * Created by aymansalah on 7/5/15.
 */
public interface UserWebService {

    @GET("/user/{user_id}")
    void getUser(@Path("user_id") Integer userId, Callback<User> callback);

    @GET("/recipe/list/user/{user_id}/page/{page}")
    void listUserArticles(@Path("user_id") Integer userId, @Path("page") Integer page, Callback<List<Article>> callback);

    @GET("/user/authenticate/{fb_user_id}/{fb_access_token}")
    void logInUserWithFacebook(@Path("fb_user_id") String fbUserId, @Path("fb_access_token") String fbAccessToken, Callback<User> callback);

    @GET("/user/logout")
    void logoutUser(Callback<Response> callback);

}
