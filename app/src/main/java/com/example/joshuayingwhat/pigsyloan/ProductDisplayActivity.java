package com.example.joshuayingwhat.pigsyloan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.joshuayingwhat.pigsyloan.webview.WebViewActivity;

/**
 * 产品详情界面
 * Created by JoshuaYingWhat on 2017/7/8.
 */
public class ProductDisplayActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodcut_layout);
        mRegister = (Button) findViewById(R.id.register_bt);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
    }
}
