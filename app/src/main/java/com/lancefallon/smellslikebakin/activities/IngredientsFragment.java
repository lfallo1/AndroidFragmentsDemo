package com.lancefallon.smellslikebakin.activities;
import com.lancefallon.smellslikebakin.dal.Recipes;

public class IngredientsFragment extends CheckBoxFragment {

    @Override
    public String[] getItems(int idx) {
        return Recipes.ingredients[idx].split("`");
    }
}
