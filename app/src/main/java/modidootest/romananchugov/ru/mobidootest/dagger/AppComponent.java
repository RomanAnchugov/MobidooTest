package modidootest.romananchugov.ru.mobidootest.dagger;

import javax.inject.Singleton;

import dagger.Component;
import modidootest.romananchugov.ru.mobidootest.MainActivity;
import modidootest.romananchugov.ru.mobidootest.mvp.main.MainActivityModel;
import modidootest.romananchugov.ru.mobidootest.mvp.news.NewsAdapter;
import modidootest.romananchugov.ru.mobidootest.ui.NewsFragment;

@Singleton
@Component(modules = {NavigationModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(NewsFragment fragment);
    void inject(MainActivityModel model);
    void inject(NewsAdapter adapter);
}
