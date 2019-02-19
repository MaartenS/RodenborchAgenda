package nl.rodenboch.rodenborchagenda.services;


import java.util.ArrayList;

import nl.rodenboch.rodenborchagenda.models.NewsResource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public final class RestService {

    private static final String API_URL = "http://192.168.10.100:8008/";

    private static RestService.EndPoint EndPoint;

    public static RestService.EndPoint getEndPoint() {
        if (EndPoint == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPoint = retrofit.create(RestService.EndPoint.class);
        }
        return EndPoint;
    }

    public interface EndPoint {
        @GET("nieuws")
        Call<ArrayList<NewsResource>> getNews();
    }
}
