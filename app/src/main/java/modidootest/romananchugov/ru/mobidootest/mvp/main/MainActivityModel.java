package modidootest.romananchugov.ru.mobidootest.mvp.main;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import modidootest.romananchugov.ru.mobidootest.MobidooTestApplication;
import modidootest.romananchugov.ru.mobidootest.network.ApiCheck;
import modidootest.romananchugov.ru.mobidootest.network.ApiCheckRequest;
import modidootest.romananchugov.ru.mobidootest.network.ApiCheckResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityModel {
    private static final String TAG = MainActivityModel.class.getSimpleName();

    private MainActivityPresenter presenter;


    @Inject
    Retrofit retrofit;
    @Inject
    Gson gson;

    private ApiCheck apiCheck;

    public MainActivityModel(MainActivityPresenter presenter){
        MobidooTestApplication.INSTANCE.getAppComponent().inject(this);
        this.presenter = presenter;
        apiCheck = retrofit.create(ApiCheck.class);

    }

    public void getRequest(){
        //        Call<String> responseString = apiCheck.checkGet();
//        responseString.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i(TAG, "onResponse(GET): " );
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i(TAG, "onFailure(GET): " + t.getLocalizedMessage());
//            }
//        });
    }

    public void postRequest(){
        Call<ApiCheckResponse> responseCall = apiCheck.checkPost(new ApiCheckRequest());
        responseCall.enqueue(new Callback<ApiCheckResponse>() {
            @Override
            public void onResponse(Call<ApiCheckResponse> call, Response<ApiCheckResponse> response) {
                if(response.body() != null) {
                    if(response.body().getUrl() != null) {
                        presenter.onResponse(response.body().getUrl());
                    }else{
                        presenter.newsPressed();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiCheckResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}
