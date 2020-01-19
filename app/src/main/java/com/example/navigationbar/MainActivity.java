package com.example.navigationbar;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Button deptbtn,noticebtn,calendarbtn,busshedulebtn;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.nav_drawer_layout );

        deptbtn=findViewById( R.id.btnDept );
        noticebtn=findViewById( R.id.btnNotice );
        calendarbtn=findViewById( R.id.btnClndr );
        busshedulebtn=findViewById( R.id.btnBus );

        deptbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ChooseDepartmentActivity.class);
                startActivity( intent );

            }
        } );
        calendarbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CalendarActivity.class);
                startActivity( intent );
            }
        } );

        drawerLayout=findViewById( R.id.drawer_layoutId );
        NavigationView navigationView=findViewById( R.id.navigationViewId );
        mtoggle=new ActionBarDrawerToggle( this,drawerLayout,R.string.open,R.string.close );
        drawerLayout.addDrawerListener( mtoggle );
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        navigationView.setNavigationItemSelectedListener(this);
        WebView webView = (WebView) findViewById( R.id.activity_main_webview );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mtoggle.onOptionsItemSelected( item )){
            return true;
        }

        if (item.getItemId()==R.id.settingId){
            Toast.makeText( MainActivity.this,"setting is selected",Toast.LENGTH_SHORT ).show();
            return true;
        }

        if (item.getItemId()==R.id.feedbackId){
            Intent intent=new Intent(getApplicationContext(),FeedbackActivity.class);
            startActivity( intent );
        }
        if (item.getItemId()==R.id.aboutusId){
            Toast.makeText( MainActivity.this,"about us is selected",Toast.LENGTH_SHORT ).show();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       switch (menuItem.getItemId()){
           case R.id.nav_studentpanel:
               Intent intent=new Intent(MainActivity.this,StudentPanelActivity.class);
               startActivity( intent );
               break;
           case R.id.nav_admin:

           if (menuItem.getItemId()==R.id.nav_admin){
               Intent intent1=new Intent(MainActivity.this,AdminActivity.class);
               startActivity( intent1 );
           }


           case R.id.nav_contact:
               if (menuItem.getItemId()==R.id.nav_contact){
                   Intent intent1=new Intent(MainActivity.this,ContactActivity.class);
                   startActivity( intent1 );
               }
               break;
           case R.id.nav_share:
               if (menuItem.getItemId()==R.id.nav_share){
                   Intent intent1=new Intent(MainActivity.this,ShareActivity.class);
                   startActivity( intent1 );
               }
               break;
       }
        return true;

    }


    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}

