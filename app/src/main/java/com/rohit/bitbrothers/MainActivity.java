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

public class MainActivity extends AppCompatActivity {
   private FirebaseAuth mAuth;
   EditText eml,pwd;
   String email,password;
   Button btn;
   TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        eml=findViewById(R.id.emailR);
        pwd=findViewById(R.id.passwordR);
        btn=findViewById(R.id.loginL);
        txt=findViewById(R.id.registrationL);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),registration.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=eml.getText().toString();
                password=pwd.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(),"Please enter email or password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    login(email,password);
                }

            }
        });


    }
    private void login(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),HomePage.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter valid id or password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
