package fr.eurecom.memorable.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        //Basically here the view that will be shown in the card for the text is created
        View view = LayoutInflater.from(context).inflate(R.layout.text_node_layout,null, false);
        EditText editText = view.findViewById(R.id.edit_text);
        editText.setText(text);
        return view;
    }

    public String getText() {
        return text;
    }
}
