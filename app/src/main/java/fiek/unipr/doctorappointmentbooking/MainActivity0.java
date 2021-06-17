package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity0 extends AppCompatActivity {
    private static int SPASH_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main1 = new Intent(MainActivity0.this,MainActivity.class);
                startActivity(main1);
                finish();
            }
        },SPASH_TIME_OUT);
    }
}