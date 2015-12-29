package com.eshraq.gosport.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.eshraq.gosport.TareekhApplication;
import com.eshraq.gosport.model.Article;
import com.eshraq.gosport.model.Category;
import com.eshraq.gosport.model.Comment;
import com.eshraq.gosport.model.Settings;
import com.eshraq.gosport.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "tareekh.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    private Dao<Settings, Integer> settingsDao = null;
    private Dao<Category, Integer> categoryDao = null;
    private Dao<Article, Integer> articleDao = null;
    private Dao<User, Integer> userDao = null;
    private Dao<Comment, Integer> commentDao = null;


    // we do this so there is only one helper
    private static DatabaseHelper helper = null;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Get the helper, possibly constructing it if necessary. For each call to this method, there should be 1 and only 1
     * call to {@link #close()}.
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new DatabaseHelper(context);
        }
        usageCounter.incrementAndGet();
        return helper;
    }


    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(TareekhApplication.LOG_TAG, "onCreate");
            TableUtils.createTable(connectionSource, Article.class);
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Comment.class);
            TableUtils.createTable(connectionSource, Settings.class);

            initData();
        } catch (SQLException e) {
            Log.e(TareekhApplication.LOG_TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
            Log.i(TareekhApplication.LOG_TAG, "onUpgrade");
    }


    private void initData(){
        Log.d(TareekhApplication.LOG_TAG, "data initiating");

        Settings settings = new Settings();
        settings.setId(1);
        try {
            settingsDao.create(settings);
        } catch (SQLException e) {
            Log.e(TareekhApplication.LOG_TAG, "Data init2 ERROR");
        }
    }

    public Dao<Category, Integer> getCategoryDao() {
        if (categoryDao == null) {
            try {
                categoryDao = getDao(Category.class);
            } catch (SQLException e) {
            }
        }
        return categoryDao;
    }


    public Dao<Settings, Integer> getSettingsDao() {
        if (settingsDao == null) {
            try {
                settingsDao = getDao(Settings.class);
            } catch (SQLException e) {
            }
        }
        return settingsDao;
    }

    public Dao<Article, Integer> getRecipeDao() {
        if (articleDao == null) {
            try {
                articleDao = getDao(Article.class);
            } catch (SQLException e) {
                Log.e(TareekhApplication.LOG_TAG, e.getMessage());
            }
        }
        return articleDao;
    }

    public Dao<User, Integer> getUserDao() {
        if (userDao == null) {
            try {
                userDao = getDao(User.class);
            } catch (SQLException e) {
            }
        }
        return userDao;
    }

    public Dao<Comment, Integer> getIngredientDao() {
        if (commentDao == null) {
            try {
                commentDao = getDao(Comment.class);
            } catch (SQLException e) {
            }
        }
        return commentDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();

        categoryDao = null;
        settingsDao = null;
        articleDao = null;
        userDao = null;
        commentDao = null;
    }
}
