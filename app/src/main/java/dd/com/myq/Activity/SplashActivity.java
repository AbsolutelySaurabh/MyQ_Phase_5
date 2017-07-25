package dd.com.myq.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.messaging.FirebaseMessaging;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dd.com.myq.Intro.PrefManager;
import dd.com.myq.R;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        prefManager = (PrefManager) getPreferences(Context.MODE_PRIVATE);
        prefManager = new PrefManager(this);

        FirebaseMessaging.getInstance().subscribeToTopic("levels");

        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

//        new Thread(new Runnable() {
//            public void run() {
//                doWork();
//                startApp();
//                finish();
//            }
//        }).start();


        new Handler().postDelayed(new Runnable() {

        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */

            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity

                if (prefManager.isFirstTimeLaunch()) {

                    Log.e("first"," ");
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();

                } else
                    if(!prefManager.isFirstTimeLaunch()){

                        Log.e("second"," ");
                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                }

            }
        }, SPLASH_TIME_OUT);


    }

    private void startApp() {
        printKeyHash();
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void doWork() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printKeyHash() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("dd.com.myq.Activity", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        }
    }
}
