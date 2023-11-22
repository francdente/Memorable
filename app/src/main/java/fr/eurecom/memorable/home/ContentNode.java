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

    public abstract View createView(Context context);



}
