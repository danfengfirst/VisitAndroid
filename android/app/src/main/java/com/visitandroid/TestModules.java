package com.visitandroid;

import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;

/**
 * Created by Danfeng on 2018/11/7.
 */

public class TestModules extends ReactContextBaseJavaModule {
    private ReactApplicationContext mContext;

    public TestModules(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        //注意 这里的名字才是RN中被调用的名字
        return "TestModules";
    }

    /**
     * 调用Android原生模块，可传递的参数类型如下
     * Boolean -> Bool
     *Integer -> Number
     * Double -> Number
     * Float -> Number
     * String -> String
     * Callback -> function
     * ReadableMap -> Object
     * ReadableArray -> Array
     * @param msg
     */
    @ReactMethod
    public void Toasts(ReadableMap msg) {
        ReadableNativeMap map= (ReadableNativeMap) msg;
        HashMap map2=map.toHashMap();
        Toast.makeText(mContext, (String) map2.get("name"), Toast.LENGTH_SHORT).show();
    }

    /**
     *Android 传递参数给RN
     * @param msg
     * @param callback
     */
    @ReactMethod
    public void ToastFinished(String msg, Callback callback) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        WritableMap map = Arguments.createMap();
        map.putInt("id", 1);
        map.putString("name", "小明");
        WritableArray array = Arguments.createArray();
        array.pushString("哈哈1");
        array.pushString("哈哈2");
        map.putArray("sub",array );
        callback.invoke(map);
    }

    /**
     * Android 传递参数给RN
     */
    @ReactMethod
    public void sendEvent(){
        WritableMap map = Arguments.createMap();
        map.putInt("id", 1);
        map.putString("name", "路人甲");
        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("sendEvent",map);
    }

    @ReactMethod
    public void startActivity(){
        Intent intent=new Intent(mContext,TestModuleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
