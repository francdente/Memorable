package fr.eurecom.memorable.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        // Instantiate a ViewPager2 and a PagerAdapter.

        ContentNode node1 = new TextNode(1,1,"te\n\n\n\n\n\n\nxtn\n\n\node1");
        ContentNode node2 = new TextNode(1,1,"te\n\n\n\n\n\n\n\n\nxtnode2");
        albums = new ArrayList<Album>();
        ArrayList<ContentNode> contentNodes = new ArrayList<ContentNode>();
        contentNodes.add(node1);
        contentNodes.add(node2);
        Album album = new Album(contentNodes);
        albums.add(album);

        pagerAdapter = new ScreenSlidePagerAdapter(getActivity(), albums);
        viewPager.setAdapter(pagerAdapter);

        return view;
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