package com.example.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.digi_yatra_12.R;

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}

