package com.lancefallon.smellslikebakin.activities.checkboxes;
import com.lancefallon.smellslikebakin.dal.Recipes;

public class DirectionsFragment extends CheckBoxFragment {

    @Override
    public String[] getItems(int idx) {
        return Recipes.directions[idx].split("`");
    }
}
