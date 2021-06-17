package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button signin,signup;
    ImageView main_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_logo = findViewById(R.id.main_logo);
        signin = findViewById(R.id.btn_signin);
        signup = findViewById(R.id.btn_signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginActivity);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signupActivity);
            }
        });

        //Animation
        main_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_logo.animate().setDuration(1000).rotationYBy(360f).start();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.switcher, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.night_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            case R.id.day_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}