package fr.eurecom.memorable.home;

import android.content.Context;
import android.view.View;

public abstract class ContentNode {

    private int album;
    private int day;

    public ContentNode(int album, int day) {
        this.album = album;
        this.day = day;
    }

    /**
     Create and return the view that will represent the given node
     */
    public abstract View createView(Context context);



}
