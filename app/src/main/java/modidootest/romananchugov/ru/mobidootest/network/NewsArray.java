package modidootest.romananchugov.ru.mobidootest.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsArray {
    @SerializedName("data")
    ArrayList<NewsPOJO> news;

    public ArrayList<NewsPOJO> getNews() {
        return news;
    }
}
