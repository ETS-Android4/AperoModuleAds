package com.ads.control.ads.application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEventFailure;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.AdjustSessionFailure;
import com.adjust.sdk.AdjustSessionSuccess;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.adjust.sdk.OnEventTrackingFailedListener;
import com.adjust.sdk.OnEventTrackingSucceededListener;
import com.adjust.sdk.OnSessionTrackingFailedListener;
import com.adjust.sdk.OnSessionTrackingSucceededListener;
import com.ads.control.ads.Admod;
import com.ads.control.ads.AppOpenManager;
import com.ads.control.util.AdjustApero;
import com.ads.control.util.AppUtil;

import java.util.List;

public abstract class AdsMultiDexApplication extends MultiDexApplication {
    public static String TAG = "AdjustApero";
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.BUILD_DEBUG = buildDebug();
        Log.i("Application", " run debug: " + AppUtil.BUILD_DEBUG );
        Admod.getInstance().init(this, getListTestDeviceId());
        if (enableAdsResume()) {
            AppOpenManager.getInstance().init(this, getOpenAppAdId());
        }
        if (enableAdjust()) {
            setupIdEvent();
            setupAdjust();
        }

    }

    private void setupIdEvent() {
        AdjustApero.enableAdjust =  true;
    }

    private void setupAdjust() {

        String environment = buildDebug() ? AdjustConfig.ENVIRONMENT_SANDBOX : AdjustConfig.ENVIRONMENT_PRODUCTION;
        Log.i("Application", "setupAdjust: "+environment);
        AdjustConfig config = new AdjustConfig(this, getAdjustToken(), environment);

        // Change the log level.
        config.setLogLevel(LogLevel.VERBOSE);
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            @Override
            public void onAttributionChanged(AdjustAttribution attribution) {
                Log.d(TAG, "Attribution callback called!");
                Log.d(TAG, "Attribution: " + attribution.toString());
            }
        });

        // Set event success tracking delegate.
        config.setOnEventTrackingSucceededListener(new OnEventTrackingSucceededListener() {
            @Override
            public void onFinishedEventTrackingSucceeded(AdjustEventSuccess eventSuccessResponseData) {
                Log.d(TAG, "Event success callback called!");
                Log.d(TAG, "Event success data: " + eventSuccessResponseData.toString());
            }
        });
        // Set event failure tracking delegate.
        config.setOnEventTrackingFailedListener(new OnEventTrackingFailedListener() {
            @Override
            public void onFinishedEventTrackingFailed(AdjustEventFailure eventFailureResponseData) {
                Log.d(TAG, "Event failure callback called!");
                Log.d(TAG, "Event failure data: " + eventFailureResponseData.toString());
            }
        });

        // Set session success tracking delegate.
        config.setOnSessionTrackingSucceededListener(new OnSessionTrackingSucceededListener() {
            @Override
            public void onFinishedSessionTrackingSucceeded(AdjustSessionSuccess sessionSuccessResponseData) {
                Log.d(TAG, "Session success callback called!");
                Log.d(TAG, "Session success data: " + sessionSuccessResponseData.toString());
            }
        });

        // Set session failure tracking delegate.
        config.setOnSessionTrackingFailedListener(new OnSessionTrackingFailedListener() {
            @Override
            public void onFinishedSessionTrackingFailed(AdjustSessionFailure sessionFailureResponseData) {
                Log.d(TAG, "Session failure callback called!");
                Log.d(TAG, "Session failure data: " + sessionFailureResponseData.toString());
            }
        });


        config.setSendInBackground(true);
        Adjust.onCreate(config);
        registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());


    }

    public abstract boolean enableAdsResume();

    public abstract List<String> getListTestDeviceId();

    public abstract String getOpenAppAdId();

    public abstract boolean enableAdjust();

//    public abstract boolean enableSandbokAdjust();

    public abstract String getAdjustToken();

    public abstract Boolean buildDebug();

    private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityResumed(Activity activity) {
            Adjust.onResume();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Adjust.onPause();
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }
    }
}
