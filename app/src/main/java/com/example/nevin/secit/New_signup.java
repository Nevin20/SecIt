package com.example.nevin.secit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class New_signup extends AppCompatActivity {

    EditText mobnum,mobotp;
    CircleImageView verification;
//    private FirebaseAuth mAuth;
//    private String mVerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);

        mobnum=findViewById(R.id.mobile_no);
        mobotp=findViewById(R.id.otp);
        verification=findViewById(R.id.cont_);

        //mAuth= FirebaseAuth.getInstance();
//
//        String mobile = mobnum.getText().toString().trim();
//        if(mobile.isEmpty() || mobile.length() < 10){
//            mobnum.setError("Enter a valid mobile");
//            mobnum.requestFocus();
//            return;
//        }




    }
}
