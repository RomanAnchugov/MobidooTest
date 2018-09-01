package modidootest.romananchugov.ru.mobidootest.network;

public class ApiCheckResponse {
    boolean canShow;
    String url;
    String bannerUrl;

    @Override
    public String toString() {
        return "Can Show: " + canShow + "\n Url: " + url + "\n bannerUrl" + bannerUrl;
    }

    public String getUrl(){
        return url;
    }
}
