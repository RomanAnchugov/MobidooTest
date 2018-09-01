package modidootest.romananchugov.ru.mobidootest.network;

import com.google.gson.annotations.SerializedName;

public class NewsPOJO {
    private String title;

    @SerializedName("short_text")
    private String shortText;

    @SerializedName("full_text")
    private String fullText;

    private String picture;
    private String coefficient;
    private String schedule;

    @SerializedName("publicated_at")
    private String publicatedAt;

    public String getTitle() {
        return title;
    }

    public String getShortText() {
        return shortText;
    }

    public String getPicture() {
        return picture;
    }

    public String getFullText() {
        return fullText;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n FullText: " + fullText + "\nPicture:" + picture;
    }
}
