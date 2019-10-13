package br.com.renegades.posupml;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AppService {
    @GET("soccerplayer/{value}")
    Call<Player> valuePlayer(@Path("value") Float value);
}
