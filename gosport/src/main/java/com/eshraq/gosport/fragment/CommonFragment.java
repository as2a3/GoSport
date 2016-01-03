package com.eshraq.gosport.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.eshraq.gosport.TareekhApplication;
import com.eshraq.gosport.webservice.CommonRequest;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by Ahmed on 12/29/2015.
 */
public class CommonFragment extends Fragment{
    private static List<Object> webServiceList;
    private static RestAdapter restAdapter;

    private static String PREF_NAME = "pref";
    private static String SESSION_ID = "sessionId ";


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    protected abstract class RequestCallback<T> implements Callback<T> {
        private CommonRequest request;

        public RequestCallback(CommonRequest request) {
            this.request = request;
        }

        @Override
        public void failure(RetrofitError error) {
            //((MainActivity)getActivity()).showConnectionError(CommonFragment.this, request, error, getView());
        }
    }

    protected abstract class PagingRequestCallback<T> implements Callback<T> {
        private CommonRequest request;

        public PagingRequestCallback(CommonRequest request) {
            this.request = request;
        }
    }

    public <T> T getWebService(Class<T> clazz) {
        if (webServiceList == null) {
            webServiceList = new ArrayList<>();
        }

        for (Object webService : webServiceList) {
            if (webService.getClass().equals(clazz)) {
                return (T) webService;
            }
        }

        MyCookieManager myCookieManager = new MyCookieManager();
        myCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(myCookieManager);

        T webService = getRestAdapter().create(clazz);
        webServiceList.add(webService);
        return webService;
    }

    protected RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    // TODO: replace from config
                    .setEndpoint("http://ishraq_content.net23.net")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            String sessionId = getSessionId();
                            if (sessionId != null)
                                requestFacade.addHeader("Cookie", sessionId);

                        }
                    })
                    .build();
        }

        return restAdapter;
    }

    class MyCookieManager extends CookieManager {
        @Override
        public void put(URI uri, Map<String, List<String>> stringListMap) throws IOException {
            super.put(uri, stringListMap);
            if (stringListMap != null && stringListMap.get("Set-Cookie") != null)
                for (String string : stringListMap.get("Set-Cookie")) {
                    saveSessionId(string);
                }
        }
    }

    public boolean saveSessionId(String sessionId ) {
        SharedPreferences.Editor editor = TareekhApplication.getContext().getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(SESSION_ID, sessionId);
        return editor.commit();
    }

    public String getSessionId() {
        SharedPreferences savedSession = TareekhApplication.getContext().getSharedPreferences(PREF_NAME, 0);
        return savedSession.getString(SESSION_ID , null);
    }
}
