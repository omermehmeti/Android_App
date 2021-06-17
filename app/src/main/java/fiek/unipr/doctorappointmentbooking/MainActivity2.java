package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    ConstraintLayout MainActivity2;
    EditText et_SA,et_S,et_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_SA = findViewById(R.id.et_SA);
        et_S = findViewById(R.id.et_S);
        et_D = findViewById(R.id.et_D);

        MainActivity2 = findViewById(R.id.MainActivity2);

        et_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewcalendar = new Intent(MainActivity2.this, Calendar.class);
                startActivity(viewcalendar);
            }
        });
    }
    //Validation
    public  void btnsearch(View view){
        String area,doc,date;
        area=et_SA.getText().toString();
        doc=et_S.getText().toString();
        date=et_D.getText().toString();
        if(area.isEmpty() && doc.isEmpty() && date.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_SA.setHint(getString(R.string.required));
            et_S.setHint(getString(R.string.required));
            et_D.setHint(getString(R.string.required));
            et_SA.setBackgroundColor(getResources().getColor(R.color.red));
            et_D.setBackgroundColor(getResources().getColor(R.color.red));
            et_S.setBackgroundColor(getResources().getColor(R.color.red));
        }else if(area.isEmpty() && doc.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_SA.setHint(getString(R.string.required));
            et_S.setHint(getString(R.string.required));
            et_SA.setBackgroundColor(getResources().getColor(R.color.red));
            et_S.setBackgroundColor(getResources().getColor(R.color.red));
        }else if(area.isEmpty() && date.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_SA.setHint(getString(R.string.required));
            et_D.setHint(getString(R.string.required));
            et_SA.setBackgroundColor(getResources().getColor(R.color.red));
            et_D.setBackgroundColor(getResources().getColor(R.color.red));
        }else if(doc.isEmpty() && date.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_S.setHint(getString(R.string.required));
            et_D.setHint(getString(R.string.required));
            et_D.setBackgroundColor(getResources().getColor(R.color.red));
            et_S.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else if(area.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_SA.setHint(getString(R.string.required));
        }
        else if(doc.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_S.setHint(getString(R.string.required));
        }else if(date.isEmpty()){
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            et_D.setHint(getString(R.string.required));
        }else{
            Snackbar.make(MainActivity2,getString(R.string.load),Snackbar.LENGTH_LONG).show();
        }
    }
}