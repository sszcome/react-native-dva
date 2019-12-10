package com.firstapp.module.sms;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SmsReceiverModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext reactContext;

    private SmsObserver mObserver;

    public SmsReceiverModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

        mObserver = new SmsObserver(reactContext,null);

    }

    @NonNull
    @Override
    public String getName() {
        return "SmsHelperModule";
    }

    @ReactMethod
    public void startSmsObserver(final Promise promise) {
        Uri uri = Uri.parse("content://sms");
        //第二个参数： 是否监听对应服务所有URI监听  例如sms 所有uri
        reactContext.getContentResolver().registerContentObserver(uri, true, mObserver);
        promise.resolve(true);
    }

    @ReactMethod
    public void stopSmsObserver(final Promise promise) {
        reactContext.getContentResolver().unregisterContentObserver(mObserver);
        promise.resolve(true);
    }
}
