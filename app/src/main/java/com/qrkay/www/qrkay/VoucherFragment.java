package com.qrkay.www.qrkay;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qrkay.www.qrkay.customviews.RecyclerViewAdapter;
import com.qrkay.www.qrkay.customviews.VoucherModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by mark2 on 19/01/2018.
 *
 */

public class VoucherFragment extends Fragment {

    ArrayList<VoucherModel> voucherModels;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager verticalLLM;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private String userID;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_voucher, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        userID = firebaseAuth.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users/" + userID +"/cards" );


        mRecyclerView = getActivity().findViewById(R.id.recyclerViewCard);
        verticalLLM = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        voucherModels = new ArrayList<>();
        voucherModels.add(new VoucherModel("imgPath", 2, 1));
        System.out.println("memes");
//        voucherModels.add(new VoucherModel("imgPath", 9, 4));
//        voucherModels.add(new VoucherModel("imgPath", 8, 3));
//        voucherModels.add(new VoucherModel("imgPath", 7, 2));
//        voucherModels.add(new VoucherModel("imgPath", 6, 1));
//        voucherModels.add(new VoucherModel("imgPath", 5, 0));
//        voucherModels.add(new VoucherModel("imgPath", 4, 2));
//        voucherModels.add(new VoucherModel("imgPath", 3, 1));
//        voucherModels.add(new VoucherModel("imgPath", 2, 0));
//        voucherModels.add(new VoucherModel("imgPath", 1, 1));


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                voucherModels.clear();

                for (DataSnapshot cardSnapshot: dataSnapshot.getChildren()){
                    VoucherModel voucherModel = cardSnapshot.getValue(VoucherModel.class);
                    voucherModels.add(voucherModel);
                    System.out.println(voucherModel.getMaxStamps());
                    System.out.println(voucherModel.getUsedStamps());
                    System.out.println(cardSnapshot.getValue());
                }


                mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), voucherModels);
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
                mRecyclerView.setLayoutManager(verticalLLM);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
