package com.qrkay.www.qrkay.customviews;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qrkay.www.qrkay.R;

import java.util.ArrayList;

/**
 * Created by eoin2 on 24/01/2018.
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<VoucherModel> modelList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mLogo;
        public LinearLayout mLine1;
        public LinearLayout mLine2;
        public TextView mBusinessName;
        public RelativeLayout mCardFront;
        public TextView mTCHeader;
        public TextView mTCs;

        private boolean isFlipped;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mLine1.setVisibility(isFlipped ? View.VISIBLE : View.GONE);
                    mLine2.setVisibility(isFlipped ? View.VISIBLE : View.GONE);
                    mBusinessName.setVisibility(isFlipped ? View.VISIBLE : View.INVISIBLE);
                    mTCHeader.setVisibility(isFlipped ? View.GONE : View.VISIBLE);
                    mTCs.setVisibility(isFlipped ? View.GONE : View.VISIBLE);

                    isFlipped = !isFlipped;
                }
            });
            mLogo = itemView.findViewById(R.id.img_logo);
            mLine1 = itemView.findViewById(R.id.line_1);
            mLine2 = itemView.findViewById(R.id.line_2);
            mBusinessName = itemView.findViewById(R.id.tv_business_name);
            mCardFront = itemView.findViewById(R.id.rl1);
            mTCHeader = itemView.findViewById(R.id.tv_TC_title);
            mTCs = itemView.findViewById(R.id.tv_TC);

            isFlipped = false;
        }
    }

    public RecyclerViewAdapter(ArrayList<VoucherModel> myDataset){
        modelList = myDataset;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        // TODO Set size, margins, paddings, layout parameters...

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        VoucherModel model = modelList.get(position);
        holder.mBusinessName.setText("Some business name");
        holder.mLogo.setImageResource(R.mipmap.ic_launcher);
        holder.mTCs.setText(model.gettAndCs());

        if(holder.mLine1.getChildCount() > 0){
            holder.mLine1.removeAllViewsInLayout();
            holder.mLine2.removeAllViewsInLayout();
        }

        Resources r = holder.mCardFront.getResources();
        int ww = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, r.getDisplayMetrics());
        // And size of padding for each stamp
        int pp = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ww, ww);
        int numPerRow = Math.round(model.getMaxStampsCard() / 2f);

        for (int i = 0; i < model.getMaxStampsCard(); i++) {
            StampView view = new StampView(holder.mCardFront.getContext(), null);
            // Set stamp padding
            view.setPadding(pp, pp, pp, pp);
            // If it's stamped, stamp it
            if (i < model.getCurrentStamps()) {
                view.setStamped(true);
            }

            // Add them to the right row
            // TODO need to fix single-row stamps for this layout
            //if (model.getCurrentStamps() < 6 || i < numPerRow)
            if (i < numPerRow)
                holder.mLine1.addView(view, params);
            else
                holder.mLine2.addView(view, params);
        }
    }

    @Override
    public int getItemCount(){
        return modelList.size();
    }
}
