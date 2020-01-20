package com.example.navigationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Bus_ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bus__schedule );

        ImageView imageView=findViewById( R.id.photo_view );
        ImageView imgview=findViewById( R.id.photo_View );
        imageView.setImageResource( R.drawable.midterm_bus );
        imgview.setImageResource( R.drawable.finalbus );
    }
}
