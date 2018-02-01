package com.fasttech.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;

public class SignUpActivity extends AppCompatActivity {
    MaterialEditText edtPhone,edtName,edtPassword;
    Button btnSignUp2;
    SpotsDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        btnSignUp2 = (Button)findViewById(R.id.btnSignUp2);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new SpotsDialog(SignUpActivity.this, R.style.Custom);
                alertDialog.setMessage("Please Wait...");
                alertDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if already exists in database
                        String name = edtName.getText().toString().trim();
                        String phone = edtPhone.getText().toString().trim();
                        String pass = edtPassword.getText().toString().trim();
                        if(name.isEmpty()||phone.isEmpty() || pass.isEmpty()){
                            alertDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Please enter your details!!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                alertDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Phone Number is already registered!!", Toast.LENGTH_SHORT).show();
                            } else {
                                alertDialog.dismiss();
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUpActivity.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
