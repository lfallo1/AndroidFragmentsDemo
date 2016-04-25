package com.lancefallon.smellslikebakin.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.activities.checkboxes.DirectionsFragment;
import com.lancefallon.smellslikebakin.activities.checkboxes.IngredientsFragment;
import com.lancefallon.smellslikebakin.activities.checkboxes.ViewPagerFragment;
import com.lancefallon.smellslikebakin.dal.Recipes;

public class DualPaneFragment extends Fragment {

    private static final String DUALPANE_INGREDIENTS_TAG = "DUALPANE_INGREDIENTS_TAG";
    private static final String DUALPANE_DIRECTIONS_TAG = "DUALPANE_DIRECTIONS_TAG";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int idx = getArguments().getInt(ViewPagerFragment.EXTRA_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[idx]);
        View view = inflater.inflate(R.layout.fragment_duelpane, container, false);

        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.EXTRA_RECIPE_INDEX, idx);

        FragmentManager fragmentManager = getChildFragmentManager();

        //ingredients fragment
        IngredientsFragment savedIngrediantsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(DUALPANE_INGREDIENTS_TAG);
        if(savedIngrediantsFragment == null){
            final IngredientsFragment ingredientsFragment = new IngredientsFragment();
            ingredientsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_dualpane_ingredients_placeholder, ingredientsFragment, DUALPANE_INGREDIENTS_TAG);
            fragmentTransaction.commit();
        }

        //directions fragment
        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DUALPANE_DIRECTIONS_TAG);
        if(savedDirectionsFragment == null){
            final DirectionsFragment directionsFragment = new DirectionsFragment();
            directionsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_dualpane_directions_placeholder, directionsFragment, DUALPANE_DIRECTIONS_TAG);
            fragmentTransaction.commit();
        }

        return view;
    }
}
