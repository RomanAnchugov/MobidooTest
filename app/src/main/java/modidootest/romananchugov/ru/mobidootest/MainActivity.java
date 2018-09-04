package modidootest.romananchugov.ru.mobidootest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import modidootest.romananchugov.ru.mobidootest.mvp.main.MainActivityPresenter;
import modidootest.romananchugov.ru.mobidootest.mvp.main.MainActivityView;
import modidootest.romananchugov.ru.mobidootest.network.NewsPOJO;
import modidootest.romananchugov.ru.mobidootest.ui.NewsFragment;
import modidootest.romananchugov.ru.mobidootest.ui.SpecificNewsFragment;
import modidootest.romananchugov.ru.mobidootest.ui.WebViewFragment;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {
    private static final String TAG = MainActivity.class.getSimpleName();

    @InjectPresenter
    MainActivityPresenter presenter;

    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;

    @BindView(R.id.navView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)

    Toolbar toolbar;

    @Inject
    NewsFragment newsFragment;

    private String currentFragment;

    private boolean isBackToolbar = false;

    @ProvidePresenter
    MainActivityPresenter providePresenter(){
        return new MainActivityPresenter(router);
    }

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey){
                case Screens.WEB_VIEW_SCREEN:
                    Fragment fragment = new WebViewFragment();
                    currentFragment = "Web View";
                    Bundle bundle = new Bundle();
                    bundle.putString(WebViewFragment.URL_KEY, (String)data);
                    isBackToolbar = true;
                    updateToolbar();
                    fragment.setArguments(bundle);
                    return fragment;
                case Screens.NEWS_SCREEN:
                    isBackToolbar = false;
                    updateToolbar();
                    return newsFragment;
                case Screens.SPECIFIC_NEWS_SCREEN:
                    SpecificNewsFragment specificNewsFragment = new SpecificNewsFragment();
                    isBackToolbar = true;
                    updateToolbar();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString(SpecificNewsFragment.NEWS_DATE, ((NewsPOJO) data).getSchedule());
                    bundle1.putString(SpecificNewsFragment.NEWS_TITLE_KEY, ((NewsPOJO) data).getTitle());
                    bundle1.putString(SpecificNewsFragment.NEWS_TEXT_KEY, ((NewsPOJO) data).getFullText());
                    bundle1.putString(SpecificNewsFragment.NEWS_IMAGE_KEY, ((NewsPOJO) data).getPicture());
                    specificNewsFragment.setArguments(bundle1);
                    Log.i(TAG, "createFragment: " + ((NewsPOJO) data).getTitle());
                    return specificNewsFragment;

            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MobidooTestApplication.INSTANCE.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentFragment = getResources().getString(R.string.news);
        ButterKnife.bind(this);

        presenter.activityOnCreate();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                switch (item.getItemId()){
                    case R.id.navNews:
                        if(!currentFragment.equals(getResources().getString(R.string.news))) {
                            presenter.newsPressed();
                        }
                        break;
                    case R.id.navFootball:
                        if(!currentFragment.equals(getResources().getString(R.string.football))) {
                            presenter.footballPressed();
                        }
                        break;
                    case R.id.navBasketball:
                        if(!currentFragment.equals(getResources().getString(R.string.basketball))) {
                            presenter.basketballPressed();
                        }
                        break;
                    case R.id.navVolleyball:
                        if(!currentFragment.equals(getResources().getString(R.string.volleyball))){
                            presenter.volleyballPressed();
                        }
                        break;
                    case R.id.navHockey:
                        if(!currentFragment.equals(getResources().getString(R.string.hockey))) {
                            presenter.hockeyPressed();
                        }
                        break;
                }


                return true;
            }
        });

        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        isBackToolbar = false;
        currentFragment = getResources().getString(R.string.news);
        navigationView.getMenu().getItem(0).setChecked(true);
        presenter.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(!isBackToolbar) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }else{
                    router.exit();
                    isBackToolbar = false;
                    currentFragment = getResources().getString(R.string.news);
                    navigationView.getMenu().getItem(0).setChecked(true);
                    updateToolbar();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showNews() {
        currentFragment = getResources().getString(R.string.news);
        newsFragment.showNews();
    }

    @Override
    public void showFootball() {
        currentFragment = getResources().getString(R.string.football);
        newsFragment.showFootball();
    }

    @Override
    public void showBasketBall() {
        currentFragment = getResources().getString(R.string.basketball);
        newsFragment.showBasketball();
    }

    @Override
    public void showVolleyBall() {
        currentFragment = getResources().getString(R.string.volleyball);
        newsFragment.showVolleyball();
    }

    @Override
    public void showHockey() {
        currentFragment = getResources().getString(R.string.hockey);
        newsFragment.showHockey();
    }

    @Override
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Web View");
    }

    @Override
    public void updateToolbar() {
        toolbar.setTitle(currentFragment);
        if(isBackToolbar){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }else{
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }
}
