package com.aqeel.johnwick.jsontry.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wallpaper {



    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "isFav")
    Boolean isFav;

    @ColumnInfo(name = "url")
    String url;

    @ColumnInfo(name = "highUrl")
    String highUrl;

    @ColumnInfo(name = "largeImageUrl")
    String largeImageURL;

    public Wallpaper(String url, String highUrl, String largeImageURL, Boolean isFav) {
        this.url = url;
        this.highUrl = highUrl;
        this.largeImageURL = largeImageURL;
        this.isFav = isFav;
    }
//
//    public Wallpaper(String url, String highUrl) {
//        this.url = url;
//        this.highUrl = highUrl;
//    }

    public Boolean getFav() {
        return isFav;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }

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

