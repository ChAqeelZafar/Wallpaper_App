package com.aqeel.johnwick.jsontry.models;

public class Wallpaper {
    String url, highUrl, largeImageURL;

    public Wallpaper(String url, String highUrl, String largeImageURL) {
        this.url = url;
        this.highUrl = highUrl;
        this.largeImageURL = largeImageURL;
    }
//
//    public Wallpaper(String url, String highUrl) {
//        this.url = url;
//        this.highUrl = highUrl;
//    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHighUrl() {
        return highUrl;
    }

    public void setHighUrl(String highUrl) {
        this.highUrl = highUrl;
    }
}
