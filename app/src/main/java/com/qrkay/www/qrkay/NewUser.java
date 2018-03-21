package com.qrkay.www.qrkay;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qrkay.www.qrkay.customviews.VoucherModel;


/**
 * Created by mark2 on 30/01/2018.
 */

public class NewUser {



//    Firebase rootRef = new Firebase("https://<url>.firebaseio.com/users/");
//    Firebase userRef = rootRef.child(mAuthData.getUid() + "/");


    public static void isNewUser() {

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String userID = firebaseAuth.getUid();


        DatabaseReference myRef = database.getReference("Users/" + userID + "/cards/welcome");

//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()) {
//
//                    DatabaseReference myRef = database.getReference("Users/" + userID + "/cards/welcome");
//                    VoucherModel welcomeVoucher = new VoucherModel.VoucherBuilder(1, 8)
//                            .voucherName("Welcome Card!")
//                            .contactEmail("Mark2502@gmail.com")
//                            .tAndCs("Terms and conditions Will be shown on the back")
//                            .build();
//                    myRef.setValue(welcomeVoucher);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError firebaseError) {
//
//            }
//        });
    }
}
