package com.ddmeng.hellonougat;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ddmeng.hellonougat.shortcuts.DynamicShortcutActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ShortcutManager shortcutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDynamicShortcuts();

        findViewById(R.id.update_dynamic_shortcuts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDynamicShortcuts();
            }
        });
    }

    private void createDynamicShortcuts() {
        shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo dynamicShortcut1 = new ShortcutInfo.Builder(this, "shortcut_blog")
                .setShortLabel("open my blog")
                .setLongLabel("Open my blog home page")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cnblogs.com/mengdd/")))
                .build();

        ShortcutInfo dynamicShortcut2 = new ShortcutInfo.Builder(this, "shortcut_dynamic")
                .setShortLabel("Dynamic Shortcut")
                .setLongLabel("Open Dynamic shortcut 2")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntents(
                        // this dynamic shortcut set up a back stack using Intents, when pressing back, will go to MainActivity
                        // the last Intent is what the shortcut really opened
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                                new Intent(DynamicShortcutActivity.ACTION_OPEN_DYNAMIC)
                                // intent's action must be set
                        })
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(dynamicShortcut1, dynamicShortcut2));

    }

    private void updateDynamicShortcuts() {
        ShortcutInfo webShortcut = new ShortcutInfo.Builder(MainActivity.this, "shortcut_blog")
                .setRank(1)
                .build();

        ShortcutInfo dynamicShortcut = new ShortcutInfo.Builder(MainActivity.this, "shortcut_dynamic")
                .setRank(0)
                .build();
        // the rank value can not be set to negative, otherwise will throw
        // java.lang.IllegalArgumentException: Rank cannot be negative or bigger than MAX_RANK

        // the static shortcuts have the rank 0, so they will always be closest to launcher icon

        shortcutManager.updateShortcuts(Arrays.asList(webShortcut, dynamicShortcut));
    }
}
