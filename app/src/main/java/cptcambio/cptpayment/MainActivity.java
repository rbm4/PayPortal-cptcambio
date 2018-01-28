package cptcambio.cptpayment;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //mWebView.loadUrl("http://payportal.cptcambio.com");
        mWebView.loadUrl("https://hackaton-cielo-novo-rbm4.c9users.io/");
        mWebView.setWebViewClient(new WebViewClient());
    }}