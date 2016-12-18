package com.example.bounswegroup2.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Enes on 18.12.2016.
 */
public class CustomInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // try the request
        Response response = chain.proceed(request);

       /* if (response shows expired token) {

            // get a new token (I use a synchronous Retrofit call)

            // create a new request and modify it accordingly using the new token
            Request newRequest = request.newBuilder()...build();

            // retry the request
            return chain.proceed(newRequest);
        }*/

        // otherwise just pass the original response on
        return response;
    }

}
