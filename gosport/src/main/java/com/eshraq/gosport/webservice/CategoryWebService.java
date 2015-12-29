package com.eshraq.gosport.webservice;

import com.eshraq.gosport.model.Category;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by aymansalah on 6/9/15.
 */
public interface CategoryWebService {
    @GET("/category/")
    void listAllCategories(Callback<List<Category>> callback);
}
