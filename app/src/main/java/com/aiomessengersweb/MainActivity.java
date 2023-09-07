package com.aiomessengersweb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;

import android.Manifest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton wuButton;
    private ImageButton tgButton;
    private ImageButton disButton;
    private ImageButton vkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wuButton = findViewById(R.id.wuButton);
        tgButton = findViewById(R.id.tgButton);
        disButton = findViewById(R.id.disButton);
        vkButton = findViewById(R.id.vkButton);

        wuButton.setOnClickListener(View ->{
            animateClick(wuButton);
            String url = "https://web.whatsapp.com/";
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, OpenUrlActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }, 200);
        });

        tgButton.setOnClickListener(View ->{
            animateClick(tgButton);
            String url = "https://web.telegram.org";
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, OpenUrlActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }, 200);
        });

        disButton.setOnClickListener(View ->{
            animateClick(disButton);
            String url = "https://discord.com/channels/@me";
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, OpenUrlActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }, 200);
        });

        vkButton.setOnClickListener(View ->{
            animateClick(vkButton);
            String url = "https://web.vk.me/";
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, OpenUrlActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }, 200);
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        List<ImageButton> imageButtons = new ArrayList<>();
        imageButtons.add(wuButton);
        imageButtons.add(tgButton);
        imageButtons.add(disButton);
        imageButtons.add(vkButton);

        if (hasFocus) {
            for (int i = 0; i < imageButtons.size(); i++) {
                ImageButton imageButton = imageButtons.get(i);
                @SuppressLint("DiscouragedApi") int resourceId = getResources().getIdentifier("img" + (i + 1), "drawable", getPackageName());
                @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(resourceId);
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                int buttonSize = Math.min(imageButton.getWidth(), imageButton.getHeight());
                int newButtonSize = (int) (buttonSize / 2);

                Bitmap scaledBitmap = Bitmap.createBitmap(newButtonSize, newButtonSize, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(scaledBitmap);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
                paint.setFilterBitmap(true);
                canvas.drawBitmap(bitmap, null, new Rect(0, 0, newButtonSize, newButtonSize), paint);

                imageButton.setImageBitmap(scaledBitmap);
            }
        }
        //else не картинку скрываем
    }

    private void animateClick(View view) {
        Animation animation = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(100);
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }
}