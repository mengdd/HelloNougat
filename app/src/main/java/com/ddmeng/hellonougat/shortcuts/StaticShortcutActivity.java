package com.ddmeng.hellonougat.shortcuts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ddmeng.hellonougat.R;

public class StaticShortcutActivity extends AppCompatActivity {

    public static final String ACTION_SHORTCUT_2 = "com.ddmeng.hellonougat.action.STATIC_SHORTCUT_2";
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_shortcut);
        text = (TextView) findViewById(R.id.text);
        String action = getIntent().getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            text.setText(R.string.static_shortcut_open_message_1);
        } else if (ACTION_SHORTCUT_2.equals(action)) {
            text.setText(R.string.static_shortcut_open_message_2);
        }
    }
}
