package com.qrkay.www.qrkay;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.glxn.qrgen.android.QRCode;

/**
 * Created by mark2 on 18/01/2018.
 */

public class QrFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private String UID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qr, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        UID = user.getUid();

        Bitmap myBitmap = QRCode.from(UID).bitmap();
        ImageView myImage = getActivity().findViewById(R.id.myQr);
        myImage.setImageBitmap(myBitmap);

        TextView myUID = getActivity().findViewById(R.id.myUID);
        myUID.setText(UID);
    }
}
