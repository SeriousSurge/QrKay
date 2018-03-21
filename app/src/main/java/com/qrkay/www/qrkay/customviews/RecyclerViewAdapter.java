package com.qrkay.www.qrkay.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<VoucherModel> modelList;

    private final int MAX_PER_ROW = 5;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public ImageView mLogo;
        public LinearLayout mLine1;
        public LinearLayout mLine2;
        public TextView mBusinessName;
        public TextView mTCHeader;
        public TextView mTCs;
        public RelativeLayout mCardFront;

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

            mCardView = itemView.findViewById(R.id.card_view);
            mLogo = itemView.findViewById(R.id.img_logo);
            mLine1 = itemView.findViewById(R.id.line_1);
            mLine2 = itemView.findViewById(R.id.line_2);
            mBusinessName = itemView.findViewById(R.id.tv_business_name);
            mTCHeader = itemView.findViewById(R.id.tv_TC_title);
            mTCs = itemView.findViewById(R.id.tv_TC);
            mCardFront = itemView.findViewById(R.id.rl1);

            isFlipped = false;
        }
    }

    public RecyclerViewAdapter(ArrayList<VoucherModel> myDataset){modelList = myDataset;}

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = null;
        switch(viewType){
            case VoucherModel.DEFAULT_STYLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_standard, parent, false);
                break;
            case VoucherModel.BGIMG_STYLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_bgimg, parent, false);
                break;
            case VoucherModel.NOIMG_STYLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_noimg, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        VoucherModel model = modelList.get(position);
        holder.mBusinessName.setText(model.getVoucherName());


        float[] hsv = new float[3];
        int x = 0;
        for(Float f : model.getHSV()){
            //System.out.println("Float: " + f);
            hsv[x++] = (f != null ? f : 0f);
            //System.out.println("float: " + hsv[x-1]);
        }
        GradientDrawable mGradientDrawable = new GradientDrawable();
        int[] colorTheme = new int[3];
        colorTheme[0] = Color.HSVToColor(hsv);
        colorTheme[1] = Color.HSVToColor(
                new float[]{
                        hsv[0],
                        Math.max(hsv[1] - 0.2f, 0.2f),
                        hsv[2]
                });
        colorTheme[2] = colorTheme[0];

        if(model.getCardType() != VoucherModel.NOIMG_STYLE)
            holder.mLogo.setImageResource(R.mipmap.ic_launcher);
        else
            holder.mLogo.setImageResource(android.R.color.transparent);

        holder.mTCs.setText(model.gettAndCs());

        Resources r = holder.mCardFront.getResources();

        System.out.println("backgroundStyle: " + model.getBackgroundStyle());
        switch(model.getBackgroundStyle()){
            case 0:
                colorTheme[1] = colorTheme[0];
                break;
            case 1:
                mGradientDrawable.setOrientation(GradientDrawable.Orientation.BL_TR);
                mGradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                break;
            case 2:
                mGradientDrawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                mGradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                break;
            case 3:
                mGradientDrawable.setGradientRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, r.getDisplayMetrics()));
                mGradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                break;
        }

        mGradientDrawable.setColors(colorTheme);

        holder.mCardFront.setBackground(mGradientDrawable);

        if(holder.mLine1.getChildCount() > 0){
            holder.mLine1.removeAllViewsInLayout();
            holder.mLine2.removeAllViewsInLayout();
        }

        int ww = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, r.getDisplayMetrics());
        // And size of padding for each stamp
        int pp = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ww, ww);
        int numPerRow = MAX_PER_ROW - 1;
        if(model.getMaxStampsCard() <= MAX_PER_ROW){
            holder.mLine2.setVisibility(View.GONE);
        }else{
            holder.mLine2.setVisibility(View.VISIBLE);
            numPerRow = Math.round(model.getMaxStampsCard() / 2f);
        }

        for (int i = 0; i < model.getMaxStampsCard(); i++) {
            StampView view = new StampView(holder.mCardFront.getContext(), null);
            // Set stamp padding
            view.setPadding(pp, pp, pp, pp);
            // 0 = round, 1 = rounded square, 2 = square
            view.setStampStyle(model.getStampStyle());
            // If it's stamped, stamp it
            if (i < model.getCurrentStamps()) {
                view.setStamped(true);
            }

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

    @Override
    public int getItemViewType(int pos){
        switch(modelList.get(pos).getCardType()){
            case 0:
                return VoucherModel.DEFAULT_STYLE;
            case 1:
                return VoucherModel.BGIMG_STYLE;
            case 2:
                return VoucherModel.NOIMG_STYLE;
            default:
                return -1;
        }
    }
}
