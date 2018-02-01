package com.fasttech.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;

public class SignInActivity extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnLogin;
    SpotsDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnLogin = (Button)findViewById(R.id.btnLogin2);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    alertDialog = new SpotsDialog(SignInActivity.this, R.style.Custom);
                    alertDialog.setMessage("Please Wait...");
                    alertDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //checking if user exists
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {

                            alertDialog.dismiss();
                            String phone = edtPhone.getText().toString().trim();
                            String pass = edtPassword.getText().toString().trim();
                            if(phone.isEmpty() || pass.isEmpty()){
                                Toast.makeText(SignInActivity.this, "Please enter your details!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                            //get user information
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent intent = new Intent(SignInActivity.this,CategoryActivity.class);
                                CurrentUser.cUser = user;
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Incorrect Password!!", Toast.LENGTH_SHORT).show();
                            }
                            }
                        }
                        else{
                            alertDialog.dismiss();
                            Toast.makeText(SignInActivity.this, "User does not exists in Database", Toast.LENGTH_SHORT).show();
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
