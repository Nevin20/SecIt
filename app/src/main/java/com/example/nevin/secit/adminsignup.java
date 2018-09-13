package com.example.nevin.secit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class adminsignup extends AppCompatActivity {
    EditText name,username,phoneno,password,conpassword;
    Button signup;
    Spinner region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsignup);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        phoneno=(EditText)findViewById(R.id.phoneno);
        password=(EditText)findViewById(R.id.password);
        conpassword=(EditText)findViewById(R.id.conpass);
        region=(Spinner)findViewById(R.id.region);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
