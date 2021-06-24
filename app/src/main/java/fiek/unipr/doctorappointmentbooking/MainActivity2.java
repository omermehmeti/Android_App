package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.ref.WeakReference;

public class MainActivity2 extends AppCompatActivity {

    TextView rate,bookAp;
    RatingBar ratingBar;
    ConstraintLayout MainActivity2;
    EditText et_SA,et_S,et_D,et_SI;
    private ProgressBar progressBar;
    Button btn_search;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Fragment fragment = new MapFragment();
        rate = findViewById(R.id.rate);
        et_SA = findViewById(R.id.et_SA);
        et_S = findViewById(R.id.et_S);
        et_D = findViewById(R.id.et_D);
        et_SI = findViewById(R.id.et_SI);
        btn_search = findViewById(R.id.btn_search);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Data");
        et_D.setText(str);
        progressBar = findViewById(R.id.progress_bar);
        ratingBar = findViewById(R.id.ratingBar);
        bookAp = findViewById(R.id.bookAp);

        MainActivity2 = findViewById(R.id.MainActivity2);

        String[] doctors = getResources().getStringArray(R.array.doctors);
        AutoCompleteTextView editText = findViewById(R.id.et_SI);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,doctors);
        editText.setAdapter(adapter);

        String[] categories = getResources().getStringArray(R.array.categories);
        AutoCompleteTextView editText1 = findViewById(R.id.et_S);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,categories);
        editText1.setAdapter(adapter1);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Users");
        mStorage=FirebaseStorage.getInstance();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String area = et_SA.getText().toString();
                String doc = et_SI.getText().toString();
                String categories = et_S.getText().toString();
                String date = et_D.getText().toString();

                UsersHelperClass helperClass = new UsersHelperClass(area,doc,categories,date);
                mRef.child(date).setValue(helperClass);
                       //.child(date)
                Toast.makeText(MainActivity2.this,"Submited succesfully!",Toast.LENGTH_SHORT).show();

//                Intent firebase = new Intent(MainActivity2.this,MainActivity3.class);
//                startActivity(firebase);
//                finish();
            }
        });
        et_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewcalendar = new Intent(MainActivity2.this, Calendar.class);
                startActivity(viewcalendar);
            }
        });
        et_SA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                        .commit();
                bookAp.setVisibility(View.GONE);
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate.setText(""+rating);
                save(rating);
            }
        });

    }



    public  void save(float f){
        SharedPreferences sharedPreferences = getSharedPreferences("folder",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating",f);
        editor.commit();
    }
    public float load(){
        SharedPreferences sharedPreferences = getSharedPreferences("folder",MODE_PRIVATE);
        float f = sharedPreferences.getFloat("rating",0f);
        return f;
    }
    //Vendosim nje thread per me bo kohen 3.1sekonda sepse progres bari eshte 3
    //Validation
    public  void startAsyncTask(View v){
        String area,doc,date;
        area=et_SA.getText().toString();
        doc=et_S.getText().toString();
        date=et_D.getText().toString();
        doc=et_SI.getText().toString();
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
        }else if(TextUtils.isEmpty(doc)){
            et_SI.setError("*Required field is empty");
            Snackbar.make(MainActivity2,getString(R.string.Filler),Snackbar.LENGTH_LONG).show();
            return;
        }else{
            Snackbar.make(MainActivity2,getString(R.string.load),Snackbar.LENGTH_LONG).show();
        }
        ExampleAsyncTask task = new ExampleAsyncTask(this);
        task.execute(3);
    }

    private static class ExampleAsyncTask extends AsyncTask<Integer,Integer,String> {
        private WeakReference<MainActivity2> activityWeakReference;
        ExampleAsyncTask(MainActivity2 mainActivity2){
            activityWeakReference = new WeakReference<MainActivity2>(mainActivity2);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity2 mainActivity2 = activityWeakReference.get();
            if(mainActivity2 == null || mainActivity2.isFinishing()){
                return;
            }
            mainActivity2.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for(int i=0; i<integers[0]; i++){
                publishProgress((i*100)/integers[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Loaded";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity2 mainActivity2 = activityWeakReference.get();
            if(mainActivity2 == null || mainActivity2.isFinishing()){
                return;
            }
            mainActivity2.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            MainActivity2 mainActivity2 = activityWeakReference.get();
            if(mainActivity2 == null || mainActivity2.isFinishing()){
                return;
            }
            Toast.makeText(mainActivity2,s, Toast.LENGTH_LONG).show();
            mainActivity2.progressBar.setProgress(0);
            mainActivity2.progressBar.setVisibility(View.INVISIBLE);
        }

    }
}