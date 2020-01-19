package com.example.navigationbar;

import android.content.Intent;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.OnBackPressedDispatcher;

public class MyWebViewClient  extends WebViewClient
{

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri uri = Uri.parse(url);
        if (uri.getHost() != null && uri.getHost().contains("example.com")) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;

    }


}

