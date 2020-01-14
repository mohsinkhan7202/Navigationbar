package com.example.navigationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private Button clearbtn, sendbtn;
    private EditText nameEV, messageEV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_feedback );

//        clearbtn = findViewById( R.id.clearbuttonId );
        sendbtn = findViewById( R.id.sendbutton );
        nameEV = findViewById( R.id.nameId );
        messageEV = findViewById( R.id.messageId );

        sendbtn.setOnClickListener( this );
       // clearbtn.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {

        try {

            String name=nameEV.getText().toString();
            String message=messageEV.getText().toString();

            if (v.getId()==R.id.sendbutton){
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType( "text/email" );
                intent.putExtra( Intent.EXTRA_EMAIL,new String[]{"mohsinkhaniiuc02@gmail.com"});
                intent.putExtra( Intent.EXTRA_SUBJECT,"Feedback from app" );
                intent.putExtra( Intent.EXTRA_TEXT,"Name: "+name +"\n Message: "+message );
                startActivity( Intent.createChooser( intent,"Feedback with" ) );


            }
            else /*if(v.getId()==R.id.clearbuttonId)*/{
                nameEV.setText( "" );
                messageEV.setText( "" );
            }

        } catch (Exception e) {
            Toast.makeText( getApplicationContext(),"Exception "+e,Toast.LENGTH_SHORT ).show();

        }


    }

    }

