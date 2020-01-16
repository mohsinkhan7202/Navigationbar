package com.example.navigationbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class StudentPanelActivity extends AppCompatActivity {
private  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_panel );

        String url = "https://www.iiuc.ac.bd";
        webView =findViewById( R.id.activity_main_webview );
        webView.setWebViewClient( new WebViewClient() );
        webView.loadUrl( url );
        webView.getSettings().setJavaScriptEnabled( true );



    }

    private void webviewshow() {
        @SuppressLint("SetJavaScriptEnabled")
        String url = "https://www.iiuc.ac.bd";
        WebView webView =findViewById( R.id.activity_main_webview );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.setWebViewClient( new WebViewClient() );
        webView.loadUrl( url );

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Toast.makeText( this, "Active", Toast.LENGTH_SHORT ).show();
    }
}
