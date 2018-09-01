package modidootest.romananchugov.ru.mobidootest.mvp.news;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import modidootest.romananchugov.ru.mobidootest.GlideApp;
import modidootest.romananchugov.ru.mobidootest.MobidooTestApplication;
import modidootest.romananchugov.ru.mobidootest.NewsType;
import modidootest.romananchugov.ru.mobidootest.R;
import modidootest.romananchugov.ru.mobidootest.network.NewsApi;
import modidootest.romananchugov.ru.mobidootest.network.NewsArray;
import modidootest.romananchugov.ru.mobidootest.network.NewsPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();

    @Inject
    Retrofit retrofit;

    private NewsPresenter presenter;

    private NewsApi newsApi;
    private ArrayList<NewsPOJO> newsList;

    public NewsAdapter(NewsPresenter presenter){
        MobidooTestApplication.INSTANCE.getAppComponent().inject(this);
        this.presenter = presenter;
        newsList = new ArrayList<>();
        loadNews(NewsType.NEWS);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
            case 1:
            case 2:
            case 6:
            case 7:
            case 8:
            case 12:
            case 13:
            case 14:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_big_card, parent, false);
                return new ViewHolder(view);
            case 3:
            case 4:
            case 5:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_list_right, parent, false);
                return  new ViewHolderList(view);
            case 9:
            case 10:
            case 11:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_list_left, parent, false);
                return  new ViewHolderList(view);
            case 15:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_two_cards, parent, false);
                return  new ViewHolderTwoCards(view);
            case 16:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_layout, parent, false);
                return  new ViewHolderEmpty(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
            case 1:
            case 2:
            case 6:
            case 7:
            case 8:
            case 12:
            case 13:
            case 14:
                ((ViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.recyclerItemClicked(newsList.get(position));
                    }
                });
                ((ViewHolder)holder).newsTitle.setText(newsList.get(position).getTitle());
                ((ViewHolder)holder).newsPreview.setText(newsList.get(position).getShortText());
                GlideApp.with(((ViewHolder)holder).cardView)
                        .load(newsList.get(position).getPicture())
                        .into(((ViewHolder)holder).newsImage);
            break;
            case 3:
            case 4:
            case 5:
            case 9:
            case 10:
            case 11:
                ((ViewHolderList)holder).constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.recyclerItemClicked(newsList.get(position));
                    }
                });
                ((ViewHolderList)holder).newsTitle.setText(newsList.get(position).getTitle());
                GlideApp.with(((ViewHolderList)holder).constraintLayout)
                        .load(newsList.get(position).getPicture())
                        .into(((ViewHolderList)holder).newsImage);
                break;
            case 15:
                ((ViewHolderTwoCards)holder).newsCard1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.recyclerItemClicked(newsList.get(position));
                    }
                });
                ((ViewHolderTwoCards)holder).newsCard2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.recyclerItemClicked(newsList.get(position));
                    }
                });
                ((ViewHolderTwoCards)holder).newsTitle1.setText(newsList.get(position).getTitle());
                ((ViewHolderTwoCards)holder).newsTitle2.setText(newsList.get(position + 1).getTitle());
                GlideApp.with(((ViewHolderTwoCards)holder).linearLayout)
                        .load(newsList.get(position).getPicture())
                        .into(((ViewHolderTwoCards)holder).newsImage1);
                GlideApp.with(((ViewHolderTwoCards)holder).linearLayout)
                        .load(newsList.get(position + 1).getPicture())
                        .into(((ViewHolderTwoCards)holder).newsImage2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 17;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        @BindView(R.id.newsTitleTv)
        TextView newsTitle;

        @BindView(R.id.newsShortTextTv)
        TextView newsPreview;

        @BindView(R.id.newsImageView)
        ImageView newsImage;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ButterKnife.bind(this, itemView);


        }

    }

    class ViewHolderList extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;

        @BindView(R.id.newsTitleTv)
        TextView newsTitle;

        @BindView(R.id.newsImageView)
        ImageView newsImage;

        public ViewHolderList(View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolderTwoCards extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;

        @BindView(R.id.card1)
        CardView newsCard1;
        @BindView(R.id.card2)
        CardView newsCard2;
        @BindView(R.id.newsImageView1)
        ImageView newsImage1;
        @BindView(R.id.newsImageView2)
        ImageView newsImage2;
        @BindView(R.id.newsTitleTv1)
        TextView newsTitle1;
        @BindView(R.id.newsTitleTv2)
        TextView newsTitle2;

        public ViewHolderTwoCards(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolderEmpty extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        public ViewHolderEmpty(View itemView) {
            super(itemView);
            this.constraintLayout = (ConstraintLayout) itemView;
        }
    }


    public void loadNews(String newsType){
        presenter.startedLoadingNews();
        newsList.clear();
        notifyDataSetChanged();
        newsApi = retrofit.create(NewsApi.class);
        if(newsType.equals(NewsType.NEWS)) {
            Call<NewsArray> newsRequest = newsApi.getNews();
            newsRequest.enqueue(new Callback<NewsArray>() {
                @Override
                public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                    if (response.body() != null) {
                        newsList = response.body().getNews();
                        notifyDataSetChanged();
                        presenter.endLoadingNews();
                    }
                }


                @Override
                public void onFailure(Call<NewsArray> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                    presenter.loadingWithError(t.getLocalizedMessage());
                }
            });
        }else if(newsType.equals(NewsType.FOOTBALL)){
            Call<NewsArray> newsRequest = newsApi.getFootballNews();
            newsRequest.enqueue(new Callback<NewsArray>() {
                @Override
                public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                    if (response.body() != null) {
                        newsList = response.body().getNews();
                        notifyDataSetChanged();
                        presenter.endLoadingNews();
                    }
                }


                @Override
                public void onFailure(Call<NewsArray> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                    presenter.loadingWithError(t.getLocalizedMessage());
                }
            });
        }else if(newsType.equals(NewsType.BASKETBALL)){
            Call<NewsArray> newsRequest = newsApi.getBasketballNews();
            newsRequest.enqueue(new Callback<NewsArray>() {
                @Override
                public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                    if (response.body() != null) {
                        newsList = response.body().getNews();
                        notifyDataSetChanged();
                        presenter.endLoadingNews();
                    }
                }


                @Override
                public void onFailure(Call<NewsArray> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                    presenter.loadingWithError(t.getLocalizedMessage());
                }
            });
        }else if(newsType.equals(NewsType.VOLLEYBALL)){
            Call<NewsArray> newsRequest = newsApi.getVolleyballNews();
            newsRequest.enqueue(new Callback<NewsArray>() {
                @Override
                public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                    if (response.body() != null) {
                        newsList = response.body().getNews();
                        notifyDataSetChanged();
                        presenter.endLoadingNews();
                    }
                }


                @Override
                public void onFailure(Call<NewsArray> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                    presenter.loadingWithError(t.getLocalizedMessage());
                }
            });
        }else if(newsType.equals(NewsType.HOCKEY)){
            Call<NewsArray> newsRequest = newsApi.getHockeyNews();
            newsRequest.enqueue(new Callback<NewsArray>() {
                @Override
                public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                    if (response.body() != null) {
                        newsList = response.body().getNews();
                        presenter.endLoadingNews();
                        notifyDataSetChanged();
                    }
                }


                @Override
                public void onFailure(Call<NewsArray> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                    presenter.loadingWithError(t.getLocalizedMessage());
                }
            });
        }

    }
}
