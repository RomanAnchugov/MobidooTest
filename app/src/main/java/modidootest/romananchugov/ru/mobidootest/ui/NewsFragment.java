package modidootest.romananchugov.ru.mobidootest.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import modidootest.romananchugov.ru.mobidootest.MainActivity;
import modidootest.romananchugov.ru.mobidootest.MobidooTestApplication;
import modidootest.romananchugov.ru.mobidootest.NewsType;
import modidootest.romananchugov.ru.mobidootest.R;
import modidootest.romananchugov.ru.mobidootest.mvp.news.NewsAdapter;
import modidootest.romananchugov.ru.mobidootest.mvp.news.NewsPresenter;
import modidootest.romananchugov.ru.mobidootest.mvp.news.NewsView;
import ru.terrakok.cicerone.Router;

public class NewsFragment extends MvpAppCompatFragment implements NewsView {
    private static final String TAG = NewsFragment.class.getSimpleName();

    @BindView(R.id.newsRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.newsLoadingProgressBar)
    ProgressBar progressBar;

    @InjectPresenter
    NewsPresenter presenter;

    @Inject
    Router router;

    private NewsAdapter newsAdapter;

    @ProvidePresenter
    NewsPresenter providePresenter(){
        return new NewsPresenter(router);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MobidooTestApplication.INSTANCE.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Log.i(TAG, "onCreateView: ");

        ButterKnife.bind(this, view);

        newsAdapter = new NewsAdapter(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);

        return view;
    }

    public void showNews(){
        if(((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.news);
        }
        newsAdapter.loadNews(NewsType.NEWS);
        recyclerView.smoothScrollToPosition(0);
    }

    public void showFootball(){
        if(((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.football);
        }
        newsAdapter.loadNews(NewsType.FOOTBALL);
        recyclerView.smoothScrollToPosition(0);
    }

    public void showBasketball(){
        if(((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.basketball);
        }
        newsAdapter.loadNews(NewsType.BASKETBALL);
        recyclerView.smoothScrollToPosition(0);
    }

    public void showVolleyball(){
        if(((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.volleyball);
        }
        newsAdapter.loadNews(NewsType.VOLLEYBALL);
        recyclerView.smoothScrollToPosition(0);
    }

    public void showHockey(){
        if(((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.hockey);
        }
        newsAdapter.loadNews(NewsType.HOCKEY);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
