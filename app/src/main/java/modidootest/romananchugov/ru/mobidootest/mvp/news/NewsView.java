package modidootest.romananchugov.ru.mobidootest.mvp.news;

import com.arellomobile.mvp.MvpView;

public interface NewsView extends MvpView {
    void showProgressBar();
    void hideProgressBar();
    void showErrorMessage(String error);
}
