package modidootest.romananchugov.ru.mobidootest.mvp.main;

import com.arellomobile.mvp.MvpView;

public interface MainActivityView extends MvpView {
    void showNews();
    void showFootball();
    void showBasketBall();
    void showVolleyBall();
    void showHockey();
    void showToolbar();
    void updateToolbar();
}
