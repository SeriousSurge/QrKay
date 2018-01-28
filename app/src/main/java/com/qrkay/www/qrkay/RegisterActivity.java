package com.qrkay.www.qrkay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qrkay.www.qrkay.customviews.VoucherModel;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this, TabActivity.class));
        }

        progressBar = new ProgressBar(this);

        buttonRegister = findViewById(R.id.buttonRegister);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewSignIn = findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

    }

    private void registerUser(){
        String  email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(!isValidEmail(email)){
            Toast.makeText(this, "Please Enter a valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are ok

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            String userID = firebaseAuth.getUid();
                            System.out.println("User id (register): " + userID);
                            DatabaseReference myRef = database.getReference("Users/"+ userID + "/cards/welcome");
                            VoucherModel welcomeVoucher = new VoucherModel.VoucherBuilder(1, 8).build();
                            //VoucherModel welcomeVoucher = new VoucherModel("imgPath", 8, 1);
                            myRef.setValue(welcomeVoucher);
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), TabActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Could Not Register, Please Try again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public void onClick(View view){
        if(view == buttonRegister) {
            registerUser();
        }
        if (view == textViewSignIn){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
