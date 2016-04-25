package com.lancefallon.smellslikebakin.activities.recyclerviews;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.activities.ListFragment;

/**
 * Created by lancefallon on 4/25/16.
 */
public class ListAdapter extends RecyclerViewAdapter {

    private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener){
        mListener = listener;
    }

    @Override
    protected void onRecipeSelected(int position) {
        mListener.onListRecipeSelected(position);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_item;
    }
}
