package modidootest.romananchugov.ru.mobidootest;

import android.app.Application;

import modidootest.romananchugov.ru.mobidootest.dagger.AppComponent;
import modidootest.romananchugov.ru.mobidootest.dagger.DaggerAppComponent;

public class MobidooTestApplication extends Application {

    public static MobidooTestApplication INSTANCE;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().build();
        }
        return appComponent;
    }
}
