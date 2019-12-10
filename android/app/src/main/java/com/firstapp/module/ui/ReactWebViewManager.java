package com.firstapp.module.ui;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ReactWebViewManager extends SimpleViewManager<WebView> {

  public static final String REACT_CLASS = "RCTWebView";

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @NonNull
  @Override
  protected WebView createViewInstance(@NonNull ThemedReactContext reactContext) {
    WebView webView = new WebView(reactContext);
    webView.setWebViewClient(new WebViewClient() {

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        webView.loadUrl(url);
        return true;
      }
    });
    return webView;
  }

  @ReactProp(name = "url")
  public void setUrl(WebView webView, String url) {
    Log.d(REACT_CLASS, "url:" + url);
    webView.loadUrl(url);
  }

  @ReactProp(name = "html")
  public void setHtml(WebView webView, String html) {
    Log.d(REACT_CLASS, "html:" + html);
    webView.loadData(html, "text/html; charset=utf-8", "UTF-8");
  }
}
