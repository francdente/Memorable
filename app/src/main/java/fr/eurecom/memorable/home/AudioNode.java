package fr.eurecom.memorable.home;

import android.content.Context;
import android.view.View;

public class AudioNode extends ContentNode{
    public AudioNode(int album, int day) {
        super(album, day);
    }

    @Override
    public View createView(Context context) {
        return null;
    }
}
