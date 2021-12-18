package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myDBHelper;
    EditText edtName, edtNumber, edtNameResultm, edtNumberResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인");


        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);





        myDBHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                //sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '" + edtName.getText().toString() + "' , "+edtNumber.getText().toString() +");");

                Cursor cursor2 = myDBHelper.getReadableDatabase().rawQuery("SELECT gName FROM groupTBL",null);
                Cursor cursor3 = myDBHelper.getReadableDatabase().rawQuery("SELECT gNumber FROM groupTBL",null);
                boolean checkDB = false;
                while ( cursor2.moveToNext()&&cursor3.moveToNext()){
                  if( ((cursor2.getString(0)).equals(edtName.getText().toString()))
                          && ((cursor3.getString(0)).equals(edtNumber.getText().toString())) ){
                      checkDB=true;
                            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(intent);
                            finish();

                   }
                }

                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",0).show();
            }
        });




    }



    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20), gNumber CHAR(20));");

            String sql = "INSERT INTO groupTBL values('team2','1234');";
            sqlDB.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);

        }

    }


}