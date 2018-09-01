package modidootest.romananchugov.ru.mobidootest.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCheck {
    @POST("api/check")
    @Headers("Content-Type: application/json")
    Call<ApiCheckResponse> checkPost(@Body ApiCheckRequest request);

    @GET("test301")
    Call<String> checkGet();
}
