package modidootest.romananchugov.ru.mobidootest.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    @GET("api/news")
    Call<NewsArray> getNews();

    @GET("api/news/football")
    Call<NewsArray> getFootballNews();

    @GET("api/news/basketball")
    Call<NewsArray> getBasketballNews();

    @GET("api/news/volleyball")
    Call<NewsArray> getVolleyballNews();

    @GET("api/news/hockey")
    Call<NewsArray> getHockeyNews();
}
