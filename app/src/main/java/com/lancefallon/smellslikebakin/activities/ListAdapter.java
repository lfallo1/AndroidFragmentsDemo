package com.lancefallon.smellslikebakin.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.dal.Recipes;

/**
 * Created by lancefallon on 4/24/16.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener){
        mListener = listener;        
    }
    
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageView;
        private TextView mTextView;
        private int mPosition;

        public ListViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.fragment_list_item_imageView);
            mTextView = (TextView)view.findViewById(R.id.fragment_list_item_textView);
            mImageView.setOnClickListener(this);
        }

        public void bindView(int index){
            mImageView.setImageResource(Recipes.resourceIds[index]);
            mTextView.setText(Recipes.names[index]);
            mPosition = index;
        }

        @Override
        public void onClick(View v) {
            if(v == mImageView){
                mListener.onListRecipeSelected(mPosition);
            }
        }
    }
}
