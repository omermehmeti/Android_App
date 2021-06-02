package fiek.unipr.doctorappointmentbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView tv_create;
    EditText et_email,et_password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_create = findViewById(R.id.tv_create);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btnlogin = findViewById(R.id.btnlogin);

        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signupActivity);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int retLogin = LoginUser(et_email.getText().toString(), et_password.getText().toString());
                if(retLogin==-1)
                    Toast.makeText(LoginActivity.this, getString(R.string.user_not_found),Toast.LENGTH_LONG).show();
                else if(retLogin == 0)
                    Toast.makeText(LoginActivity.this, getString(R.string.wrong_credentials),Toast.LENGTH_LONG).show();
                else
                {
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

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            String dbUserMail = cursor.getString(0);
            String dbUserPassword = cursor.getString(1);

            cursor.close();
            objDb.close();
            if(password.equals(dbUserPassword))
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
}