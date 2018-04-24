package com.qrkay.www.qrkay;

import android.animation.PropertyValuesHolder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qrkay.www.qrkay.customviews.VoucherModel;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseVoucher;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        uid = user.getUid();

        databaseVoucher = FirebaseDatabase.getInstance().getReference();


        textViewUserEmail = getActivity().findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(user.getEmail());

        buttonLogout = getActivity().findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
    }


    public void onClick(View view){
        if(view == buttonLogout){
            firebaseAuth.signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
}
