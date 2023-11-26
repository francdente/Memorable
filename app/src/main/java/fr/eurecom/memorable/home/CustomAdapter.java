package fr.eurecom.memorable.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import fr.eurecom.memorable.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final int TEXT_LAYOUT = 0;
    private static final int IMAGE_LAYOUT = 1;
    private static final int AUDIO_LAYOUT = 2;
    private Album album;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout view;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.view = view.findViewById(R.id.linear_layout);
        }
        public void fillContent(ContentNode node){
            view.addView(node.createView(view.getContext()));
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param album Album containing the data to populate views to be used
     * by RecyclerView
     */
    public CustomAdapter(Album album) {
        this.album = album;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ContentNode node = album.getNodes().get(position);
        viewHolder.fillContent(node);
    }


    @Override
    public int getItemViewType(int position) {
        ContentNode node = album.getNodes().get(position);
        if (node instanceof TextNode)
            return TEXT_LAYOUT;

        return super.getItemViewType(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return album.getLength();
    }
}

