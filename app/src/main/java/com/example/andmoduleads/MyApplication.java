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


public class MyApplication extends AdsMultiDexApplication {


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
        return Collections.singletonList("07F6B55DA3A08766A4465F36354C7EF6");
    }

    @Override
    public Map<String,String> getListEventDefaultsAdjust() {
        Map<String,String> idAdjust = new HashMap<>() ;
        idAdjust.put(AdjustApero.KEY_REVENUE_SPLASH,"totyui");
        idAdjust.put(AdjustApero.KEY_REVENUE_BANNER,"m55gsj");
        idAdjust.put(AdjustApero.KEY_REVENUE_INTER,"trg4vs");
        idAdjust.put(AdjustApero.KEY_REVENUE_NATIVE,"gztbg3");
        idAdjust.put(AdjustApero.KEY_REVENUE_RESUME,"om2b6m");
        idAdjust.put(AdjustApero.KEY_REVENUE_REWARD,"omlfur");
        return idAdjust;
    }

    @Override
    public String getOpenAppAdId() {
        return AppOpenManager.AD_UNIT_ID_TEST;
    }

    @Override
    public boolean enableAdjust() {
        return true;
    }

//    @Override
//    public boolean enableSandbokAdjust() {
//        return true;
//    }

    @Override
    public String getAdjustToken() {
        return "elikb9nnvx8g";
    }
}
