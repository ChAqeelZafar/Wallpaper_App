package com.aqeel.johnwick.jsontry.extras;

import com.aqeel.johnwick.jsontry.models.Wallpaper;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Wallpaper.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WallpaperDao wallpaperDao();
}