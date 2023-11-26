package fr.eurecom.memorable.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.ArrayList;

import fr.eurecom.memorable.R;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPager;
    private static final int NUM_PAGES = 5;
    private FragmentStateAdapter pagerAdapter;
    private ArrayList<Album> albums;


    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Find the view pager and set sliding animation
        viewPager = view.findViewById(R.id.pager);
        viewPager.setPageTransformer(new CompositePageTransformer());

        //Hard-coded initilization data
        ContentNode node1 = new TextNode(1,1,"textnode1");
        ContentNode node2 = new TextNode(1,1,"textnode2");
        ContentNode node3 = new TextNode(1,1,"textnode3");
        ContentNode node4 = new TextNode(1,1,"textnode4");
        ContentNode node5 = new ImageNode(1, 1, "landscape1", R.drawable.image1);
        ContentNode node6 = new ImageNode(1, 1, "landscape2", R.drawable.image2);
        ContentNode node7 = new ImageNode(1, 1, "landscape3", R.drawable.image3);
        albums = new ArrayList<Album>();
        ArrayList<ContentNode> contentNodes1 = new ArrayList<ContentNode>();
        ArrayList<ContentNode> contentNodes2 = new ArrayList<ContentNode>();
        ArrayList<ContentNode> contentNodes3 = new ArrayList<ContentNode>();
        contentNodes1.add(node1);
        contentNodes1.add(node2);
        contentNodes1.add(node5);
        contentNodes2.add(node3);
        contentNodes2.add(node6);
        contentNodes3.add(node4);
        contentNodes3.add(node7);
        Album album1 = new Album(1, "TODAY", contentNodes1);
        Album album2 = new Album(2, "Album 2", contentNodes2);
        Album album3 = new Album(3, "Album 3", contentNodes3);
        albums.add(album1);
        albums.add(album2);
        albums.add(album3);

        // Instantiate pagerAdapter for slider
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity(), albums);
        viewPager.setAdapter(pagerAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        private ArrayList<Album> albums;
        public ScreenSlidePagerAdapter(FragmentActivity fa, ArrayList<Album> albums) {
            super(fa);
            this.albums = albums;
        }

        @Override
        public Fragment createFragment(int position) {
            return new ScreenSlidePageFragment(albums.get(position));
        }

        @Override
        public int getItemCount() {
            return albums.size();
        }
    }
    private class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well.
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1).
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}