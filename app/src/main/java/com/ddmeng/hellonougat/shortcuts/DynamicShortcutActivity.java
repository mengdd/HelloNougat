package com.ddmeng.hellonougat.shortcuts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ddmeng.hellonougat.R;

public class DynamicShortcutActivity extends AppCompatActivity {

    public static final String ACTION_OPEN_DYNAMIC = "com.ddmeng.hellonougat.action.DYNAMIC_OPEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_shortcut);
    }
}
