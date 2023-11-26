package fr.eurecom.memorable.home;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import fr.eurecom.memorable.R;

public class TextNode extends ContentNode {

    private String text;

    //Layout things
    LinearLayout layout;
    EditText details;
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

        // Finding layout elements for enabling collapsing and expanding
        layout = view.findViewById(R.id.linear_layout);
        details = editText;
        //Set listener for making it collapse and expand
        TextView textView = view.findViewById(R.id.author);
        textView.setOnClickListener(this::toggle);

        //Enable layout animations
        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        return view;
    }

    private void toggle(View view){
        int v = (details.getVisibility() == View.GONE)? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        details.setVisibility(v);
    }
    public String getText() {
        return text;
    }
}
