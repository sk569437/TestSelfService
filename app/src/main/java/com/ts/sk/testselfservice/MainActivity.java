package com.ts.sk.testselfservice;

import android.app.SelfManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SelfService";
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
    }

    private void testSelfService() {
        try {
            SelfManager sm = (SelfManager) getSystemService("self");
            if(sm == null) {
                Log.e(TAG, "self manager service is null....");
                return;
            }
            Log.d(TAG, "self manager before called....");

            int ret = sm.selfAddNumber(3, 4);
            Log.d(TAG, "self manager add num is:" + ret);

            String ret2 = sm.selfAddString("hello self service");
            Log.d(TAG, "self manager add String is:" + ret2);

        }catch (Exception e){
            Log.e(TAG, "self manager invoke method get exception, msg:" + e.getMessage());
        }
    }
}
