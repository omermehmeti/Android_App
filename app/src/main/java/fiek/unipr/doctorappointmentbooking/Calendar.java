package fiek.unipr.doctorappointmentbooking;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {
    EditText editText;
    CalendarView calendarView;
    String selectedDate;
    Button btnsave,btnupdate,btndelete;
    SQLiteDatabase objDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        editText = findViewById(R.id.editText);
        calendarView = findViewById(R.id.calendarView);
        btnsave = findViewById(R.id.btnsave);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
                ReadDatabase(view);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDatabase(this);
                Toast.makeText(Calendar.this,"Eventi u ruajt me sukses!",Toast.LENGTH_LONG).show();
            }
        });
//        btnupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UpdateDatabase(this);
//                Toast.makeText(Calendar.this,"Eventi u perditesua me sukses!",Toast.LENGTH_LONG).show();
//            }
//        });
    }
    public void InsertDatabase(View.OnClickListener view){
            objDb = new DatabaseHelper(Calendar.this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Date",selectedDate);
            contentValues.put("Event", editText.getText().toString());
            objDb.insert("EventCalendar", null, contentValues);

    }
//    public void UpdateDatabase(View.OnClickListener view){
//        objDb = new DatabaseHelper(Calendar.this).getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Date",selectedDate);
//        contentValues.put("Event", editText.getText().toString());
//        Cursor cursor = objDb.rawQuery("Select * from EventCalendar whe")
//    }

    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date = " + selectedDate;
        try{
            Cursor cursor = objDb.rawQuery(query, null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editText.setText("");
        }
    }
}