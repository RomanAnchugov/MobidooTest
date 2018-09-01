package modidootest.romananchugov.ru.mobidootest.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import modidootest.romananchugov.ru.mobidootest.R;
import modidootest.romananchugov.ru.mobidootest.mvp.web.WebViewFragmentView;

public class WebViewFragment extends MvpAppCompatFragment implements WebViewFragmentView{
    private static final String TAG = WebViewFragment.class.getSimpleName();
    public static final String URL_KEY = "url";

    private String url;

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.requestProgressBar)
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            url = bundle.getString(URL_KEY);
        }else{
            url = "https://www.google.ru/";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        ButterKnife.bind(this, view);
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            
        };

        webView.setWebViewClient(webViewClient);
        webView.loadUrl(url);

        return view;
    }
}
