package com.example.digi_yatra_12;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.fragments.retrieved_addheerdata;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(mWebViewClient);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "android");
        webView.loadUrl("https://api.digitallocker.gov.in/public/oauth2/1/authorize?client_id=B3F1B8A6&redirect_uri=https://www.DigiYatraFoundation.com/&state=1252a&response_type=code");
    }
    WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            handleAuthorizeresponseURL(request.getUrl());
            super.onReceivedError(view, request, error);
        }
    };

    class MyJavaScriptInterface {
        @JavascriptInterface
        public void onUrlChange(String url) {
            Log.d("check urls", "onUrlChange" + url);
            Uri uri = Uri.parse(url);
            handleAuthorizeresponseURL(uri);
        }
    }
    public void handleAuthorizeresponseURL(Uri url) {
        Log.d("check url", url.toString());
        if (url.toString().contains("https://www.digiyatrafoundation.com/") || url.toString().contains("https://digiyatrafoundation.com/")) {
            String code = url.getQueryParameter("code");
            Log.d("check url", code);
            Intent intent = new Intent(this, retrieved_addheerdata.class);
            intent.putExtra("code",code);
            startActivity(intent);
            finish();
        }
    }
}