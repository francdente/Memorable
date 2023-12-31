package fr.eurecom.memorable.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.eurecom.memorable.R;
import fr.eurecom.memorable.home.contentNodes.TextNode;

public class ScreenSlidePageFragment extends Fragment {
    private RecyclerViewAdapter recyclerViewAdapter;
    private Album album;
    private FloatingActionButton mAddFab, mAddAudioFab, mAddTextFab, mAddImageFab;
    private Boolean isAllFabsVisible;
    public ScreenSlidePageFragment(Album album) {
        this.album = album;
        recyclerViewAdapter = new RecyclerViewAdapter(album);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set album name
        Button button = view.findViewById(R.id.title_button);
        button.setText(album.getTitle());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(recyclerViewAdapter);
        setFloatingButton(view);

    }

    private void setFloatingButton(View view) {
        mAddFab = view.findViewById(R.id.add_fab);

        mAddAudioFab = view.findViewById(R.id.add_audio);
        mAddImageFab = view.findViewById(R.id.add_image);
        mAddTextFab = view.findViewById(R.id.add_text);

        mAddAudioFab.setVisibility(View.GONE);
        mAddImageFab.setVisibility(View.GONE);
        mAddTextFab.setVisibility(View.GONE);

        isAllFabsVisible = false;

        mAddFab.setOnClickListener(v -> {
            if (!isAllFabsVisible) {
                mAddAudioFab.show();
                mAddImageFab.show();
                mAddTextFab.show();

                isAllFabsVisible = true;
            } else {
                mAddAudioFab.setVisibility(View.GONE);
                mAddImageFab.setVisibility(View.GONE);
                mAddTextFab.setVisibility(View.GONE);

                isAllFabsVisible = false;
            }
        });
        mAddAudioFab.setOnClickListener(
                v -> {
                   Toast.makeText(getContext(),"Audio added", Toast.LENGTH_SHORT).show();
                });
        mAddImageFab.setOnClickListener(
                v -> Toast.makeText(getContext(), "Image Added", Toast.LENGTH_SHORT
                ).show());
        mAddTextFab.setOnClickListener(
                v -> {
                    album.addNode(new TextNode(album.getId(), 0, ""));
                    recyclerViewAdapter.notifyItemInserted(album.getNodes().size());
                });
    }

}