package fr.eurecom.memorable.home;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import fr.eurecom.memorable.R;

public class ImageNode extends ContentNode{
    private LinearLayout layout;
    private EditText details;
    private ImageView imageView;
    private String text;
    private int image;
    public ImageNode(int album, int day, String text, int image) {
        super(album, day);
        this.text = text;
        this.image = image;
    }

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_node_layout,null, false);

        // Finding layout elements for enabling collapsing and expanding
        layout = view.findViewById(R.id.linear_layout);
        details = view.findViewById(R.id.edit_text);
        imageView = view.findViewById(R.id.image_view);

        // Set content of the view
        imageView.setImageDrawable(context.getDrawable(image));
        details.setText(text);

        //Set listener for making it collapse and expand
        TextView textView = view.findViewById(R.id.author);
        textView.setOnClickListener(this::toggle);

        //Enable layout animations
        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        return view;

    }

    private void toggle(View view) {
        int v = (details.getVisibility() == View.GONE)? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        details.setVisibility(v);
        imageView.setVisibility(v);
    }
}
