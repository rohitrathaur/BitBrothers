package com.rohit.bitbrothers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button button;
    EditText eml,pass;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        button=(Button)findViewById(R.id.loginL) ;
        eml=findViewById(R.id.emailR);
        pass=findViewById(R.id.passwordR);
        txt=(TextView) findViewById(R.id.loginR);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

     button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String email=eml.getText().toString();
             String password=pass.getText().toString();

             if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
             {
                 Toast.makeText(getApplicationContext(),"Please fill all fields!",Toast.LENGTH_LONG).show();
             }
             else
             {
                 mAuth.createUserWithEmailAndPassword(email,password)
                         .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (task.isSuccessful()) {
                                     Toast.makeText(getApplicationContext(),"Registration Successfully",Toast.LENGTH_LONG).show();
                                     Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                     startActivity(i);
                                 }
                                 else {
                                     Toast.makeText(getApplicationContext(),"Eroor",Toast.LENGTH_LONG).show();

                                 }
                             }
                         });
             }

         }
     });

    }
}