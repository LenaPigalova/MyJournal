package com.example.asus.myjournal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class JournalActivity extends AppCompatActivity {

    private TextView dateTV;
    private EditText etFIO;
    private EditText etPhone;
    private EditText etComment;
    private LinearLayout linearLayoutDialogAddStudent;
    private LinearLayout linearLayoutJournal;
    private Integer numberOfStudent;
    private EditText etTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_journal);
        dateTV = (TextView)findViewById(R.id.date_text_view);
        Calendar calendar = Calendar.getInstance();
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        dateTV.setText(day.toString() + "." + "0" + month.toString());
        linearLayoutJournal = (LinearLayout)findViewById(R.id.journal_linear_layout);
        numberOfStudent = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student_theme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_student_item){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            linearLayoutDialogAddStudent = new LinearLayout(getApplicationContext());
            linearLayoutDialogAddStudent.setOrientation(LinearLayout.VERTICAL);
            builder.setTitle("Введите название предмета.");
            final TextView tvFio = new TextView(getApplicationContext());
            tvFio.setText("Фамилия Имя Отчество");
            etFIO = new EditText(getApplicationContext());
            etFIO.setTextColor(Color.BLACK);
            TextView tvPhone = new TextView(getApplicationContext());
            tvPhone.setText("Телефон");
            etComment = new EditText(getApplicationContext());
            etComment.setTextColor(Color.BLACK);
            TextView tvComment = new TextView(getApplicationContext());
            tvComment.setText("Комментарии");
            etPhone = new EditText(getApplicationContext());
            etPhone.setTextColor(Color.BLACK);
            linearLayoutDialogAddStudent.addView(tvFio);
            linearLayoutDialogAddStudent.addView(etFIO);
            linearLayoutDialogAddStudent.addView(tvPhone);
            linearLayoutDialogAddStudent.addView(etPhone);
            linearLayoutDialogAddStudent.addView(tvComment);
            linearLayoutDialogAddStudent.addView(etComment);
            builder.setView(linearLayoutDialogAddStudent);
            builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LinearLayout linearLayoutJournalName = new LinearLayout(getApplicationContext());
                    linearLayoutJournalName.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayoutJournalName.setLayoutParams(new
                            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    TextView tvNumber = new TextView(getApplicationContext());
                    tvNumber.setBackgroundResource(R.drawable.border_text_view);
                    tvNumber.setTextColor(Color.parseColor("#ee302c2c"));
                    tvNumber.setLayoutParams(new
                            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                    tvNumber.setText(numberOfStudent.toString());
                    numberOfStudent++;

                    TextView tvStudent = new TextView(getApplicationContext());
                    tvStudent.setLayoutParams(new
                            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 5f));
                    tvStudent.setText(etFIO.getText().toString());
                    tvStudent.setBackgroundResource(R.drawable.border_text_view);
                    tvStudent.setTextColor(Color.parseColor("#ee302c2c"));


                    TextView tvMark = new TextView(getApplicationContext());
                    tvMark.setLayoutParams(new
                            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                    tvMark.setText("");
                    tvMark.setBackgroundResource(R.drawable.border_text_view);
                    tvMark.setTextColor(Color.RED);

                    linearLayoutJournalName.addView(tvNumber);
                    linearLayoutJournalName.addView(tvStudent);
                    linearLayoutJournalName.addView(tvMark);
                    linearLayoutJournal.addView(linearLayoutJournalName);
                }
            });
            builder.setNegativeButton("Закрыть", null);
            builder.show();
        }
        if(item.getItemId() == R.id.change_theme_item){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Введите тему");
            etTheme = new EditText(getApplicationContext());
            etTheme.setTextColor(Color.BLACK);
            alert.setView(etTheme);
            alert.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    JournalActivity.super.setTitle(etTheme.getText());
                }
            });
            alert.setNegativeButton("Закрыть", null);
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
