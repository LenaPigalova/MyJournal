package com.example.asus.myjournal;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> buttons;
    private String nameButton;
    private LinearLayout linearLayout;
    private Button btn;
    private EditText et;
    private String dbPuth;
    private ArrayList<Predmet> predmets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttons = new ArrayList<Button>();
        predmets = new ArrayList<Predmet>();

        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        SQLiteDataBaseHelper dbHalper = new SQLiteDataBaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHalper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from lesson", null);
        predmets.clear();
        if(cursor.moveToFirst()) {
            do {
                Predmet predmet = new Predmet();
                predmet.setIdOfPredmet(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                predmet.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                predmets.add(predmet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //new PredmetDownloadTask().execute();

        for (Predmet p:predmets) {
            btn = new Button(getApplicationContext());
            btn.setTextColor(Color.BLACK);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ChooseClassActivity.class);
                    startActivity(intent);
                }
            });
            btn.setText(p.getName());
            linearLayout.addView(btn);
            buttons.add(btn);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.plus){

            et = new EditText(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Введите название предмета.");
            builder.setView(et);
            builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    btn = new Button(getApplicationContext());
                    btn.setTextColor(Color.BLACK);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), ChooseClassActivity.class);
                            startActivity(intent);
                        }
                    });
                    btn.setText(et.getText().toString());

                    SQLiteDataBaseHelper dbHalper = new SQLiteDataBaseHelper(MainActivity.this);
                    SQLiteDatabase db = dbHalper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", et.getText().toString());
                    db.insert("lesson", null, contentValues);
                    linearLayout.addView(btn);
                    buttons.add(btn);
                }
            });
            builder.setNegativeButton("Закрыть", null);
            builder.show();


        }
        return super.onOptionsItemSelected(item);
    }

    public class PredmetDownloadTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            SQLiteDataBaseHelper dbHalper = new SQLiteDataBaseHelper(MainActivity.this);
            SQLiteDatabase db = dbHalper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from lesson", null);
            predmets.clear();
            if(cursor.moveToFirst()) {
                do {
                    Predmet predmet = new Predmet();
                    predmet.setIdOfPredmet(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    predmet.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    predmets.add(predmet);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return true;
        }

        @Override
        protected void onPostExecute ( Boolean success ) {
            super . onPostExecute ( success );
        }

    }
}
