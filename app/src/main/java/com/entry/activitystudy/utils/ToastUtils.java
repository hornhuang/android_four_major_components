package com.entry.activitystudy.utils;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public final class ToastUtils {

    public static void makeText(AppCompatActivity context, String toastwords){
        Toast.makeText(context, toastwords, Toast.LENGTH_SHORT).show();
    }
}
