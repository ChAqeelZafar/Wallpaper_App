package com.aqeel.johnwick.jsontry.extras;

import com.aqeel.johnwick.jsontry.models.Wallpaper;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WallpaperDao {
    @Query("SELECT * FROM wallpaper")
    List<Wallpaper> getAll();



    @Insert
    void insertAll(Wallpaper... wallpapers);

    @Delete
    void delete(Wallpaper wallpaper);

    @Query("DELETE FROM wallpaper WHERE largeImageUrl = :lg")
    void deleteWallpaper(String lg);
}

