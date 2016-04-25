package com.lancefallon.smellslikebakin.activities.checkboxes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.activities.checkboxes.DirectionsFragment;
import com.lancefallon.smellslikebakin.activities.checkboxes.IngredientsFragment;
import com.lancefallon.smellslikebakin.dal.Recipes;

public class ViewPagerFragment extends Fragment {

    public static final String EXTRA_RECIPE_INDEX = "EXTRA_RECIPE_INDEX";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int idx = getArguments().getInt(EXTRA_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[idx]);
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_RECIPE_INDEX, idx);

        //ingredients fragment
        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(bundle);

        //directions fragment
        final DirectionsFragment directionsFragment = new DirectionsFragment();
        directionsFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.fragment_viewpager_viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? ingredientsFragment : directionsFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.fragment_viewpager_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
