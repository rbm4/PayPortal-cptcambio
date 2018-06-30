package payportal.cptcambio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Ricardo on 28/06/2018.
 */

public class LandPageActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Deseja sair?")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LandPageActivity.super.onBackPressed();
                        System.exit(1);
                    }
                })
                .setNegativeButton("Voltar", null)
                .show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land_page_layout);
        mWebView = findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://payportal.cptcambio.com");
        //mWebView.loadUrl("https://hackaton-cielo-novo-rbm4.c9users.io/");
        mWebView.setWebViewClient(new WebViewClient());
    }
}