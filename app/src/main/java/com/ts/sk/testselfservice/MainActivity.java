package com.ts.sk.testselfservice;

import android.app.Activity;
import android.app.SelfManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    private static final String TAG = "SelfService";

    private SelfManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testSelfService();
            }
        });
        findViewById(R.id.btn_install).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInstall();
            }
        });
        findViewById(R.id.btn_uninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUninstall();
            }
        });

        sm = (SelfManager) getSystemService("self");
        if(sm == null) {
            Log.e(TAG, "self manager service is null....");
            return;
        }
    }

    private void testSelfService() {
        try {
            if(sm == null) {
                Log.e(TAG, "self manager service is null....");
                return;
            }

            Log.d(TAG, "self manager before called....");

            int ret = sm.selfAddNumber(3, 4);
            Log.d(TAG, "self manager add num is:" + ret);

            String ret2 = sm.selfAddString("hello self service");
            Log.d(TAG, "self manager add String is:" + ret2);

			//sm.silentInstallApk();
			//silentUninstallApk(Context context, String packageName)

        }catch (Exception e){
            Log.e(TAG, "self manager invoke method get exception, msg:" + e.getMessage());
        }
    }

    private void onInstall() {
        try {
            if(sm == null) {
                Log.e(TAG, "[onInstall]self manager service is null....");
                return;
            }

            Log.d(TAG, "self manager before called....");

            String packageName = "laclaveganadora.translator";
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Translator.apk";
            Log.d(TAG, "onInstall with file path:" + path);
            sm.silentInstallApk(this, packageName, path);

            //sm.silentInstallApk();
            //silentUninstallApk(Context context, String packageName)

        }catch (Exception e){
            Log.e(TAG, "self manager invoke method onInstall get exception, msg:" + e.getMessage());
        }
    }

    private void onUninstall() {
        try {
            if(sm == null) {
                Log.e(TAG, "[onUninstall]self manager service is null....");
                return;
            }

            Log.d(TAG, "onUninstall before called....");

            String packageName = "laclaveganadora.translator";
            sm.silentUninstallApk(this, packageName);
            Log.d(TAG, "onUninstall called after.");

            //sm.silentInstallApk();
            //silentUninstallApk(Context context, String packageName)

        }catch (Exception e){
            Log.e(TAG, "self manager invoke method onUninstall get exception, msg:" + e.getMessage());
        }
    }
}
