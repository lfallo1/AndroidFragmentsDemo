package com.lancefallon.smellslikebakin.activities.checkboxes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.dal.Recipes;

/**
 * Created by lancefallon on 4/25/16.
 */
public abstract class CheckBoxFragment extends Fragment  {

    public static final String CHECKBOX_STATES = "CHECKBOX_STATES";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);

        int idx = getArguments().getInt(ViewPagerFragment.EXTRA_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[idx]);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_directions_directionsLayout);
        String[] items = getItems(idx);

        boolean[] checkboxStates = new boolean[items.length];
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(CHECKBOX_STATES) != null){
            checkboxStates = savedInstanceState.getBooleanArray(CHECKBOX_STATES);
        }

        setupCheckboxes(items, linearLayout, checkboxStates);
        return view;
    }

    public abstract String[] getItems(int idx);

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean[] checkboxStates = new boolean[mCheckBoxes.length];
        for (int i = 0; i < mCheckBoxes.length; i++){
            checkboxStates[i] = mCheckBoxes[i].isChecked();
        }
        outState.putBooleanArray(CHECKBOX_STATES, checkboxStates);
    }

    private void setupCheckboxes(String[] items, ViewGroup container, boolean[] checkboxStates){
        mCheckBoxes = new CheckBox[items.length];
        for(int i = 0; i < items.length; i++){
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setPadding(16, 16, 16, 16);
            checkBox.setText(items[i]);
            checkBox.setTextSize(20f);
            container.addView(checkBox);
            checkBox.setChecked(checkboxStates[i]);
            mCheckBoxes[i] = checkBox;
        }
    }
}