package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity {

    ConstraintLayout MainActivity2;
    TextView tv_create;
    EditText et_email,et_password;
    Button btnlogin,btnCreate;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_create = findViewById(R.id.tv_create);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btnlogin = findViewById(R.id.btnlogin);
        btnCreate = findViewById(R.id.btnCreate);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        MainActivity2 = findViewById(R.id.MainActivity2);


        //Validation for email
        awesomeValidation.addValidation(this,R.id.et_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //Validation for password
        awesomeValidation.addValidation(this,R.id.et_password,
                ".{8,}",R.string.invalid_password);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signupActivity);
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login form validation
                if(awesomeValidation.validate()){
                    //On succes
                    Toast.makeText(getApplicationContext(),"Form Validate Successfuly..",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Validation Faild",Toast.LENGTH_SHORT).show();
                }

                int retLogin = LoginUser(et_email.getText().toString(), et_password.getText().toString());

                if(retLogin==-1) {
                    Toast.makeText(LoginActivity.this, getString(R.string.user_not_found), Toast.LENGTH_LONG).show();
                }else if(retLogin == 0) {
                    Toast.makeText(LoginActivity.this, getString(R.string.wrong_credentials), Toast.LENGTH_LONG).show();
                }else {
                    Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity2.class);
                    startActivity(mainActivityIntent);
                }
            }

        });
    }



    private int LoginUser(String email, String password)
    {
        SQLiteDatabase objDb = new DatabaseHelper(LoginActivity.this).getReadableDatabase();
        Cursor cursor = objDb.query(DatabaseModelHelper.UsersTable,new String[]{DatabaseModelHelper.UsersEmail,DatabaseModelHelper.UsersPassword},DatabaseModelHelper.UsersEmail+"=?",
                new String[]{email},"","","");

        if(cursor.getCount()>1)
        {
            cursor.moveToFirst();
            String dbUserMail = cursor.getString(0);
            String dbUserPassword = cursor.getString(1);

            cursor.close();
            objDb.close();
            if(password.equals(dbUserPassword) && email.equals(dbUserMail) && password.length()>0 && email.length()>0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

        return -1;
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