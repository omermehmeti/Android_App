package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    TextView tv_sgin1;
    EditText etfullname,etmobilenumber,etemail,etpassword;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etfullname = findViewById(R.id.et_fullname);
        etmobilenumber = findViewById(R.id.et_mobilenumber);
        etemail = findViewById(R.id.et_email);
        etpassword = findViewById(R.id.et_password);
        tv_sgin1 = findViewById(R.id.tv_sign1);

        tv_sgin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        btnCreate = findViewById(R.id.btnCreate);



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase objDb = new DatabaseHelper(SignUpActivity.this).getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseModelHelper.UsersFullName, etfullname.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersMobileNumber, etmobilenumber.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersEmail, etemail.getText().toString());
                contentValues.put(DatabaseModelHelper.UsersPassword, etpassword.getText().toString());

                try
                {
                    long id = objDb.insert(DatabaseModelHelper.UsersTable,null,contentValues);
                    if(id>0)
                        Toast.makeText(SignUpActivity.this,getString(R.string.success_message),Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(SignUpActivity.this,ex.getMessage(),Toast.LENGTH_LONG).show();
                }
                finally {
                    objDb.close();
                }
            }
        });
    }
}