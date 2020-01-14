package com.example.navigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.nav_drawer_layout );

        drawerLayout=findViewById( R.id.drawer_layoutId );
        mtoggle=new ActionBarDrawerToggle( this,drawerLayout,R.string.open,R.string.close );
        drawerLayout.addDrawerListener( mtoggle );
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

    }


//    private boolean onOptionItemSlected(@NonNull MenuItem item) {
//        if (mtoggle.onOptionsItemSelected( item )) {
//            return true;
//        }
//        return super.onOptionsItemSelected( item );
//    }


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
}
