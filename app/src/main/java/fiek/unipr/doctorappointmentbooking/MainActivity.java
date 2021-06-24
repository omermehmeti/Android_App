package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button signin,signup,mainPlaces;
    ImageView main_logo;
    RelativeLayout background1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_logo = findViewById(R.id.main_logo);
        signin = findViewById(R.id.btn_signin);
        signup = findViewById(R.id.btn_signup);
        mainPlaces = findViewById(R.id.mainPlaces);
        background1 = findViewById(R.id.background1);



        mainPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rowlayout = new Intent(MainActivity.this,PlacesActivity.class);
                startActivity(rowlayout);
            }
        });



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
    public int getRandomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        background1.setBackgroundColor(color);
        return color;
    }
    public boolean dispatchKeyEvent(KeyEvent event) {

        int action,keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:
            {
                if(KeyEvent.ACTION_UP == action){

                    getRandomColor();
                }
                break;
            }
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            {
                if(KeyEvent.ACTION_DOWN == action)
                {
                    getRandomColor();
                }
            }
            break;
        }
        return super.dispatchKeyEvent(event);
    }

}