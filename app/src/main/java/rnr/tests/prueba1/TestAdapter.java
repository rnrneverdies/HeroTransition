package rnr.tests.prueba1;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private static final String TAG = "PromosAdapter";

    private ArrayList<String> mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom viewholder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView mCardView;
        private final ImageView mImageView;
        private final TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mCardView = (CardView) v;
            mImageView = (ImageView) v.findViewById(R.id.imageView);
            mTextView = (TextView) v.findViewById(R.id.textView);
        }
    }

    public TestAdapter(ArrayList<String> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        ViewCompat.setTransitionName(viewHolder.mCardView, "cardViewTransition" + position);
        ViewCompat.setTransitionName(viewHolder.mImageView, "imageTransition" + position);
        ViewCompat.setTransitionName(viewHolder.mTextView, "textTransition" + position);

        viewHolder.mTextView.setText(mDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void add(String text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mDataSet.remove(position);
        //notifyItemRemoved(position);
    }
}