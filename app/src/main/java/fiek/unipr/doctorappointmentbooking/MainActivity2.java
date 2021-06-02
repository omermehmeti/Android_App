package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText et_SA,et_S,et_D;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_SA = findViewById(R.id.et_SA);
        et_S = findViewById(R.id.et_S);
        et_D = findViewById(R.id.et_D);

        et_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewcalendar = new Intent(MainActivity2.this, Calendar.class);
                startActivity(viewcalendar);
            }
        });
    }
}