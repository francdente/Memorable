package fr.eurecom.memorable.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import fr.eurecom.memorable.R;

public class ScreenSlidePageFragment extends Fragment {
    private CustomAdapter customAdapter;
    private Album album;
    private FloatingActionButton mAddFab, mAddAudioFab, mAddTextFab, mAddImageFab;
    private Boolean isAllFabsVisible;
    public ScreenSlidePageFragment(Album album) {
        this.album = album;
        customAdapter = new CustomAdapter(album);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
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
                    customAdapter.notifyItemInserted(album.getNodes().size());
                });
    }

}