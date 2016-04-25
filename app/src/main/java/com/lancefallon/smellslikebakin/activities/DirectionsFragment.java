package com.lancefallon.smellslikebakin.activities;
import com.lancefallon.smellslikebakin.dal.Recipes;

public class DirectionsFragment extends CheckBoxFragment {

    @Override
    public String[] getItems(int idx) {
        return Recipes.directions[idx].split("`");
    }
}
