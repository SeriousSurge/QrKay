package com.qrkay.www.qrkay.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qrkay.www.qrkay.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by eoin2 on 24/01/2018.
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {
    private ArrayList<VoucherModel> modelList;
    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context ctx, ArrayList<VoucherModel> data){
        layoutInflater = LayoutInflater.from(ctx);
        modelList = data;
    }

    @Override
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new ItemHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemHolder holder, int position){
        holder.setItemDetails(modelList.get(position));
    }

    @Override
    public int getItemCount(){
        return modelList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(ItemHolder item, int position);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewAdapter parent;

        private final int MAX_SINGLE_ROW = 6;

        RelativeLayout gl1;
        RelativeLayout gl2;
        ImageView img_1;
        ImageView img_2;
        LinearLayout line_1;
        LinearLayout line_2;
        TextView tv_business_name;
        TextView tv_TC_title;
        TextView tv_TC;

        public ItemHolder(View itemView, RecyclerViewAdapter parent){
            super(itemView);
            itemView.setOnClickListener(this);
            this.parent = parent;

            gl1 = itemView.findViewById(R.id.gl1);
            gl2 = itemView.findViewById(R.id.gl2);
            img_1 = itemView.findViewById(R.id.img_logo);
            img_2 = itemView.findViewById(R.id.img_logo_1);
            line_1 = itemView.findViewById(R.id.line_1);
            line_2 = itemView.findViewById(R.id.line_2);
            tv_business_name = itemView.findViewById(R.id.tv_business_name);
            tv_TC_title = itemView.findViewById(R.id.tv_TC_title);
            tv_TC = itemView.findViewById(R.id.tv_TC);
        }

        public void setItemDetails(VoucherModel card){
            // This is a bit hacky, but seems to work OK for now
            // Stops extra "stamps" from getting added each time view is drawn
            if(line_1.getChildCount() > 0) {
                line_1.removeAllViewsInLayout();
                line_2.removeAllViewsInLayout();
            }

            img_1.setImageResource(R.mipmap.ic_launcher);
            img_2.setImageResource(R.mipmap.ic_launcher_round);
            tv_business_name.setText("<Some business name><and again><and again>");
            tv_TC.setText(card.gettAndCs());

            // Set size of stamps
            // Say, 32dp (half of logo image)
            Resources r = gl1.getResources();
            int ww = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, r.getDisplayMetrics());
            // And size of padding for each stamp
            int pp = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());
            // And margin
            int mm = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ww, ww);
            for (int i = 0; i < card.getMaxStampsCard(); i++) {
                int numPerRow = Math.round(card.getMaxStampsCard() / 2f);
                // Use gl1 to get context easier than passing around
                StampView view = new StampView(gl1.getContext(), null);
                // Set stamp padding
                view.setPadding(pp, pp, pp, pp);
                // If it's stamped, stamp it
                if (i < card.getCurrentStamps()) {
                    view.setStamped(true);
                }

                // If we're setting up a single row, we need to set properties
                // As things are redrawn, we need to reset if there's two rows as well
                RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(line_1.getLayoutParams());
                //glParams.columnSpec = GridLayout.spec(1);
                //glParams.rowSpec = card.getMaxStampsCard() < MAX_SINGLE_ROW ?
                //        GridLayout.spec(1, 2)
                //        : GridLayout.spec(1);
                //glParams.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                glParams.setMargins(mm, mm, mm, mm);
                //line_1.setLayoutParams(glParams);

                // Add them to the right row
                if (card.getMaxStampsCard() < MAX_SINGLE_ROW || i < numPerRow)
                    line_1.addView(view, params);
                else
                    line_2.addView(view, params);
            }
        }

        @Override
        public void onClick(View v){
            // Toggle the two GridLayouts to switch between front/back
            gl1.setVisibility(gl1.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            gl2.setVisibility(gl2.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }
    }
}
