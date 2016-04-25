package com.lancefallon.smellslikebakin.activities.recyclerviews;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.activities.GridFragment;

public class GridAdapter extends RecyclerViewAdapter{
    private final GridFragment.OnRecipeSelectedInterface mListener;

    public GridAdapter(GridFragment.OnRecipeSelectedInterface listener){
        mListener = listener;
    }

    @Override
    protected void onRecipeSelected(int position) {
        mListener.onGridRecipeSelected(position);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grid_item;
    }
}
