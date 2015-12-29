package com.eshraq.gosport.webservice;

import com.eshraq.gosport.model.Article;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by mahmoud on 6/2/15.
 */
public interface ArticleWebService {

    @GET("/article/list/")
    void listAllArticles(Callback<List<Article>> callback);

    @GET("/article/list/{page}")
    void listPageArticles(@Path("page") Integer page, Callback<List<Article>> callback);

    @GET("/article/list/category/{article_id}/page/{page}")
    void listCategoryArticles(@Path("article_id") Integer articleId, @Path("page") Integer page, Callback<List<Article>> callback);

    @GET("/article/{article_id}")
    void getArticle(@Path("article_id") Integer articleId, Callback<Article> callback);

    @GET("/article/{article_id}/togglelike")
    void likeArticle(@Path("article_id") Integer articleId, Callback<Article> callback);

    @GET("/article/list/{page}/{token}")
    void searchArticle(@Path("page") Integer page, @Path("token") String token, Callback<List<Article>> callback);
}
