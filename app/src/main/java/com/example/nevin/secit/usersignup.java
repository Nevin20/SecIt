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

public class usersignup extends AppCompatActivity {
    EditText servicename,branchid,username,password,confirmpassword;
    Spinner region;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);
        servicename=(EditText)findViewById(R.id.servicename);
        branchid=(EditText)findViewById(R.id.branchid);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.userpass);
        confirmpassword=(EditText)findViewById(R.id.conuserconpass);
        region= (Spinner) findViewById(R.id.region1);
        submit=(Button)findViewById(R.id.submit);

        String[] arraySpinner = new String[] {"Puducherry", "Karaikal", "Mahe", "Yanam"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        region.setAdapter(spinnerAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String userServiceName,userUserName,userBranchID,userPassword,userConfirmPassword,userRegion;
                userServiceName=servicename.getText().toString();
                userUserName=username.getText().toString();
                userBranchID=branchid.getText().toString();
                userPassword=password.getText().toString();
                userConfirmPassword=confirmpassword.getText().toString();
                userRegion=region.getSelectedItem().toString();

                System.out.println("Button Clicked.."+userServiceName+userUserName+userBranchID+userPassword+userConfirmPassword+userRegion);

                if(!(userServiceName.equals("")||userUserName.equals("")||userBranchID.equals("")||userPassword.equals("")||userConfirmPassword.equals("")||userRegion.equals("")))
                {
                    if(userConfirmPassword.equals(userPassword))
                    {
                        //To Database ...

                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast toast = Toast.makeText(usersignup.this, "Confirm Password Doesn't Match", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        confirmpassword.setText("");
                        password.setText("");
                        toast.show();
                    }
                }
                else {
                    Toast toast = Toast.makeText(usersignup.this, "All Fields are Mandatory", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });


    }
}
