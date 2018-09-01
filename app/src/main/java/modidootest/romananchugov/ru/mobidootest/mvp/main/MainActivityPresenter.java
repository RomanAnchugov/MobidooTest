package modidootest.romananchugov.ru.mobidootest.mvp.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import modidootest.romananchugov.ru.mobidootest.Screens;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    private MainActivityModel networkModel;
    private Router router;

    public MainActivityPresenter(Router router){
        this.router = router;
        networkModel = new MainActivityModel(this);
    }

    public void activityOnCreate(){
        router.newRootScreen(Screens.NEWS_SCREEN);
        networkModel.postRequest();
    }

    public void onResponse(String url){
        getViewState().showToolbar();
        router.navigateTo(Screens.WEB_VIEW_SCREEN, url);
    }

    public void onBackPressed(){
        getViewState().updateToolbar();
        router.exit();
    }

    public void newsPressed(){
        getViewState().showNews();
    }

    public void footballPressed(){
        getViewState().showFootball();
    }

    public void basketballPressed(){
        getViewState().showBasketBall();
    }

    public void volleyballPressed(){
        getViewState().showVolleyBall();
    }

    public void hockeyPressed(){
        getViewState().showHockey();
    }
}
