package com.techhuntdevelopers.library.util;

import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by jrvansuita on 20/02/17.
 */

public final class VisibleUtil {

    public static void handle(View v, @Nullable String s) {
        v.setVisibility(s == null || s.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
