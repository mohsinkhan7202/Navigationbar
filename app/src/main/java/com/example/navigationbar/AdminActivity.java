package com.example.navigationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private EditText username,pass;
    private Button btn;
    private TextView textView;
    private int counter=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin );

        username=findViewById( R.id.nameId );
        pass=findViewById( R.id.passwordId );
        btn=findViewById( R.id.loginId );
        textView=findViewById( R.id.textviewId );
        textView.setText( "Number of attempts remaining :3" );

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=username.getText().toString();
                String password=pass.getText().toString();

                if (name.equals( "admin" )&& password.equals( "12345" ))
                {
                    Intent intent=new Intent(AdminActivity.this,SecondActivity.class);
                    startActivity( intent );
                }
                else{
                    counter--;
                    textView.setText("Number of attempts remaining "+counter  );
                    if(counter==0)
                    {
                        btn.setEnabled( false );
                    }
                }


            }
        } );
    }
}

