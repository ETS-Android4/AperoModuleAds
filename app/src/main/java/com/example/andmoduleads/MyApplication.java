package com.example.andmoduleads;

import android.util.Log;

import com.ads.control.AdjustApero;
import com.ads.control.AdsApplication;
import com.ads.control.AdsMultiDexApplication;
import com.ads.control.AppOpenManager;
import com.ads.control.AppPurchase;
import com.ads.control.funtion.PurchaseListioner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyApplication extends AdsApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        //        AppOpenManager.getInstance().setSplashActivity(SplashActivity.class, AppOpenManager.AD_UNIT_ID_TEST, 10000);
        AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity.class);
        List<String> listINAPId = new ArrayList<>();
        listINAPId.add(MainActivity.PRODUCT_ID);
        List<String> listSubsId = new ArrayList<>();

        AppPurchase.getInstance().initBilling(this,listINAPId,listSubsId);
//        AppPurchase.getInstance().addProductId(MainActivity.PRODUCT_ID);
    }

    @Override
    public boolean enableAdsResume() {
        return true;
    }

    @Override
    public List<String> getListTestDeviceId() {
        return Collections.singletonList("0C415A270DF82EA01C765E69924B9327");
    }


    @Override
    public String getOpenAppAdId() {
        return AppOpenManager.AD_UNIT_ID_TEST;
    }

    @Override
    public boolean enableAdjust() {
        return false;
    }

    @Override
    public String getAdjustToken() {
        return "2fm9gkqubvpc";
    }
}
