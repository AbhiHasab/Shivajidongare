package com.abhijeethasabe.shivajidongare;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.flaviofaria.kenburnsview.KenBurnsView;

public class Splash extends Activity {

    private static int b = 5000;
    KenBurnsView a;
    private Handler c;
    private Runnable d;
    ImageView splash;

    public Splash() {
    }


    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_splash);

        a = (KenBurnsView) findViewById(R.id.splash_image);
        splash = (ImageView) findViewById(R.id.splash_logo);


        Animation anim = AnimationUtils.loadAnimation(Splash.this, R.anim.translate_top_to_center);
        anim.setInterpolator((new AccelerateDecelerateInterpolator()));
        anim.setFillAfter(true);
        splash.setAnimation(anim);

        c = new Handler();
        d = new _cls1();
        c.postDelayed(d, b);
        c.sendEmptyMessage(1);

    }



    private class _cls1
            implements Runnable {

        final Splash a;

        public void run() {
            Intent intent = new Intent(a, MainActivity.class);
            a.startActivity(intent);
            finish();
            /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Splash.this);
            boolean status = preferences.getBoolean(Constants.KEY_LOGIN, false);
            if (status) {
                Intent intent = new Intent(a, HomeActivity.class);
                a.startActivity(intent);
                a.finish();

            } else {
                Intent intent = new Intent(a, SignInActivity.class);
                a.startActivity(intent);
                a.finish();

            }
*/

        }

        _cls1() {
            super();
            a = Splash.this;

        }
    }


}
