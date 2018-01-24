package com.qrkay.www.qrkay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qrkay.www.qrkay.customviews.RecyclerViewAdapter;
import com.qrkay.www.qrkay.customviews.VoucherModel;

import java.util.ArrayList;

/**
 * Created by mark2 on 19/01/2018.
 *
 */

public class VoucherFragment extends Fragment {

    ArrayList<VoucherModel> voucherModels;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager verticalLLM;
    private RecyclerView.Adapter mRecyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_voucher, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = getActivity().findViewById(R.id.recyclerViewCard);
        verticalLLM = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        voucherModels = new ArrayList<>();
        voucherModels.add(new VoucherModel("imgPath", 10, 5));
        voucherModels.add(new VoucherModel("imgPath", 9, 4));
        voucherModels.add(new VoucherModel("imgPath", 8, 3));
        voucherModels.add(new VoucherModel("imgPath", 7, 2));
        voucherModels.add(new VoucherModel("imgPath", 6, 1));
        voucherModels.add(new VoucherModel("imgPath", 5, 0));
        voucherModels.add(new VoucherModel("imgPath", 4, 2));
        voucherModels.add(new VoucherModel("imgPath", 3, 1));
        voucherModels.add(new VoucherModel("imgPath", 2, 0));
        voucherModels.add(new VoucherModel("imgPath", 1, 1));
        mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), voucherModels);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(verticalLLM);
    }
}
