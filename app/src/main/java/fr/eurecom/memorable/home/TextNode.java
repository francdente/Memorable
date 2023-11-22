package fr.eurecom.memorable.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import fr.eurecom.memorable.R;

public class TextNode extends ContentNode {

    private String text;
    public TextNode(int album, int day, String text) {
        super(album, day);
        this.text = text;
    }

    @Override
    public View createView(Context context) {
        TextView textView = new TextView(context);
        textView.setText(this.text);
        textView.setTextColor(context.getResources().getColor(R.color.black));
        textView.setTextSize(70);
        return textView;
    }
}
