package com.example.macstudio.photoappjava.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.macstudio.photoappjava.AppConstants;
import com.example.macstudio.photoappjava.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);

        mWebView.setWebViewClient(getWebViewClient());


        setContentView(mWebView);
        mWebView.loadUrl(AppConstants.LOGIN_URL);
    }

    private WebViewClient getWebViewClient() {
        return new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                final String requestUrl = request.getUrl().toString();
                if (requestUrl.startsWith(AppConstants.TTAM_URL)) {
                    final Pattern pattern = Pattern.compile("#id_token=(.*?)&");
                    String id_token = null;
                    Matcher matcher = pattern.matcher(requestUrl);
                    while(matcher.find()) {
                        id_token = matcher.group(1);
                    }
                    if (id_token != null) {
                        startPhotoFeedActivity(id_token);
                    }
                    return true;
                }


                //extract id_token and set Authorization
                return super.shouldOverrideUrlLoading(view, request);
            }
        };
    }

    private void startPhotoFeedActivity(final String id_token) {
        final Intent intent = new Intent(getApplicationContext(), PhotoFeedActivity.class);
        intent.putExtra("Authorization", id_token);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}
