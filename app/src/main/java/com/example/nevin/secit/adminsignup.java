package com.example.nevin.secit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class adminsignup extends AppCompatActivity {
    EditText name,username,phoneno,password,conpassword;
    Button signup;
    Spinner region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsignup);

        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.mailid);
        phoneno=(EditText)findViewById(R.id.phoneno);
        password=(EditText)findViewById(R.id.pass);
        conpassword=(EditText)findViewById(R.id.conpass);
        region=(Spinner)findViewById(R.id.region);

        String[] arraySpinner = new String[] {"Puducherry", "Karaikal", "Mahe", "Yanam"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        region.setAdapter(spinnerAdapter);

        signup=(Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String adminName,adminUserName,adminPhone,adminPass,adminRegion,adminCPass;
                adminName=name.getText().toString();
                adminUserName=username.getText().toString();
                adminPhone=phoneno.getText().toString();
                adminPass=password.getText().toString();
                adminCPass=conpassword.getText().toString();
                adminRegion=region.getSelectedItem().toString();

                if(!(adminName.equals("")||adminUserName.equals("")||adminPhone.equals("")||adminPass.equals("")||adminCPass.equals("")||adminRegion.equals("")))
                {
                    if(adminCPass.equals(adminPass))
                    {
                        //To Database ...

                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast  toast = Toast.makeText(adminsignup.this, "Confirm Password Doesn't Match", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        conpassword.setText("");
                        password.setText("");
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(adminsignup.this, "All Fields are Mandatory", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });

    }
}
