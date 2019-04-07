package com.example.nevin.secit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class New_signup extends AppCompatActivity {
    EditText monum ;
    CircleImageView cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);
        monum = findViewById(R.id.mobile_no);
        cont =(CircleImageView) findViewById(R.id.buttoncon);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile = monum.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    monum.setError("Enter a valid mobile");
                    monum.requestFocus();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(),OTP.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);

            }
        });
    }
}
