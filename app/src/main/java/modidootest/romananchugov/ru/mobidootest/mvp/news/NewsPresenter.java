package modidootest.romananchugov.ru.mobidootest.mvp.news;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import modidootest.romananchugov.ru.mobidootest.Screens;
import modidootest.romananchugov.ru.mobidootest.network.NewsPOJO;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {
    private static final String TAG = NewsPresenter.class.getSimpleName();

    private Router router;

    public NewsPresenter(Router router){
        this.router = router;
    }

    public void recyclerItemClicked(NewsPOJO newsPOJO){
        Log.i(TAG, "recyclerItemClicked: " + newsPOJO.toString() );
        router.navigateTo(Screens.SPECIFIC_NEWS_SCREEN, newsPOJO);
    }

    public void startedLoadingNews(){
        getViewState().showProgressBar();
    }

    public void endLoadingNews(){
        getViewState().hideProgressBar();
    }

    public void loadingWithError(String error){
        getViewState().showErrorMessage(error);
    }
}
