package com.aqeel.johnwick.jsontry.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wallpaper {



    @ColumnInfo(name = "previewImgUrl")
    String previewImgUrl;

    @ColumnInfo(name = "webformatURL")
    String webformatURL;

    @ColumnInfo(name = "largeImageUrl")
    String largeImageURL;

    @ColumnInfo(name = "highUrl")
    String highUrl;


    @ColumnInfo(name = "isFav")
    Boolean isFav;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "imgDetails")
    String imgDetails;

    public Wallpaper(String previewImgUrl, String webformatURL, String largeImageURL, String highUrl, Boolean isFav, String imgDetails) {
        this.previewImgUrl = previewImgUrl;
        this.webformatURL = webformatURL;
        this.largeImageURL = largeImageURL;
        this.highUrl = highUrl;
        this.isFav = isFav;
        this.imgDetails = imgDetails;
    }

    public String getImgDetails() {
        return imgDetails;
    }

    public void setImgDetails(String imgDetails) {
        this.imgDetails = imgDetails;
    }
//    public Wallpaper(String previewImgUrl, String highUrl, String largeImageURL, Boolean isFav) {
//        this.previewImgUrl = previewImgUrl;
//        this.highUrl = highUrl;
//        this.largeImageURL = largeImageURL;
//        this.isFav = isFav;
//    }
//
//    public Wallpaper(String url, String highUrl) {
//        this.url = url;
//        this.highUrl = highUrl;
//    }

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



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
        return previewImgUrl;
    }

    public void setUrl(String url) {
        this.previewImgUrl = url;
    }

    public String getHighUrl() {
        return highUrl;
    }

    public void setHighUrl(String highUrl) {
        this.highUrl = highUrl;
    }
}

