package com.firstapp.module.sms;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class SmsObserver extends ContentObserver {

    private static final String TAG = "SmsObserver";

    private static final String SMS_EVENT = "com.first.app:SmsEvent";

    private static final String MESSAGE_KEY = "message";

    private ReactApplicationContext mContext;

    public SmsObserver(ReactApplicationContext context, Handler handler) {
        super(handler);
        mContext = context;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

        // 第一遍 先执行content://sms/raw 第二遍则 uri.toString :content://sms/inbox
        if (uri.toString().startsWith("content://sms/raw")) {
            return;
        }

        Uri inboxUri = Uri.parse("content://sms/inbox");
        Cursor c = mContext.getContentResolver().query(inboxUri,
                new String[]{"_id", "body"}, null, null, "_id desc");
        if (null != c && c.getCount() > 0) {
            if (c.moveToFirst()) {
                String body = c.getString(c.getColumnIndex("body"));
                Log.d(TAG,body);
                emitJSEvent(MESSAGE_KEY,body);
            }
            c.close();
        }
    }

    private void emitJSEvent(@NonNull final String key, final String message) {
        if (mContext == null) {
            return;
        }

        if (!mContext.hasActiveCatalystInstance()) {
            return;
        }

        WritableNativeMap map = new WritableNativeMap();
        map.putString(key, message);

        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(SMS_EVENT, map);
    }
}
