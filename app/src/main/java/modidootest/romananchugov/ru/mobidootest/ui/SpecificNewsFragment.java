package modidootest.romananchugov.ru.mobidootest.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import modidootest.romananchugov.ru.mobidootest.GlideApp;
import modidootest.romananchugov.ru.mobidootest.MainActivity;
import modidootest.romananchugov.ru.mobidootest.R;

public class SpecificNewsFragment extends MvpAppCompatFragment {
    public static final String NEWS_TITLE_KEY = "news title";
    public static final String NEWS_TEXT_KEY = "news text";
    public static final String NEWS_IMAGE_KEY = "news image";
    public static final String NEWS_DATE = "news date";


    private static final String TAG = SpecificNewsFragment.class.getSimpleName();

    private String newsTitle;
    private String newsText;
    private String newsImage;
    private String date;
    @BindView(R.id.newsImageView)
    ImageView newsImageView;
    @BindView(R.id.newsDate)
    TextView dateTextView;
    @BindView(R.id.newsText)
    TextView newsTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specific_news, container, false);
        Bundle bundle = getArguments();
        if(bundle != null) {
            newsTitle = bundle.getString(NEWS_TITLE_KEY);
            newsText = bundle.getString(NEWS_TEXT_KEY);
            newsImage = bundle.getString(NEWS_IMAGE_KEY);
            date = bundle.getString(NEWS_DATE);
        }

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(newsTitle);
        Log.i(TAG, "onCreateView: " + newsTitle);

        ButterKnife.bind(this, view);

        GlideApp.with(view)
                .load(newsImage)
                .into(newsImageView);

        dateTextView.setText(date);
        newsTextView.setText(newsText);

        return view;
    }
}
