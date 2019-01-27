package com.techhuntdevelopers.library.builder;

import android.graphics.Bitmap;
import android.view.View;

import com.techhuntdevelopers.library.views.ViewIdGenerator;

/**
 * Created by jrvansuita on 11/02/17.
 */
public final class Item {

    private int id;
    private String label;
    private Bitmap icon;
    private View.OnClickListener onClick;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public View.OnClickListener getOnClick() {
        return onClick;
    }

    public void setOnClick(View.OnClickListener onClick) {
        this.onClick = onClick;
    }

    public int getId() {
        return id;
    }

    public Item(Bitmap icon, String label, View.OnClickListener onClick) {
        this.id = ViewIdGenerator.generateViewId();
        this.label = label;
        this.icon = icon;
        this.onClick = onClick;
    }
}
