package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    TextView tv_sgin1;
    EditText etfullname,etmobilenumber,etemail,etpassword;
    Button btnCreate,btnlogin;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etfullname = findViewById(R.id.et_fullname);
        etmobilenumber = findViewById(R.id.et_mobilenumber);
        etemail = findViewById(R.id.et_email);
        etpassword = findViewById(R.id.et_password);
        tv_sgin1 = findViewById(R.id.tv_sign1);
        btnCreate = findViewById(R.id.btnCreate);
        btnlogin = findViewById(R.id.btnlogin);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Validation for fullname
        awesomeValidation.addValidation(this,R.id.et_fullname, RegexTemplate.NOT_EMPTY,
                R.string.invalid_name);
        //Validation for mobilenumber
        awesomeValidation.addValidation(this,R.id.et_mobilenumber,
                Patterns.PHONE,R.string.invalid_mobilenumber);
        //Validation for email
        awesomeValidation.addValidation(this,R.id.et_email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //Validation for password
        awesomeValidation.addValidation(this,R.id.et_password,
                ".{8,}",R.string.invalid_password);
          btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignUp form validation
//                if(awesomeValidation.validate()){
//                    //On succes
//                    Toast.makeText(getApplicationContext(),"Form Validate Successfuly..",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(),"Validation Faild",Toast.LENGTH_SHORT).show();
//                }
                //Database content
                SQLiteDatabase objDb = new DatabaseHelper(SignUpActivity.this).getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseModelHelper.UsersFullName, etfullname.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersMobileNumber, etmobilenumber.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersEmail, etemail.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersPassword, etpassword.getText().toString());

                try
                {
                    long id = objDb.insert(DatabaseModelHelper.UsersTable,null,contentValues);
                    if(id == 4)
                        Toast.makeText(SignUpActivity.this,getString(R.string.success_message),Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(SignUpActivity.this,getString(R.string.FailedToRegister),Toast.LENGTH_LONG).show();
                }
                finally {
                    objDb.close();
                }

            }

        });
    }
}