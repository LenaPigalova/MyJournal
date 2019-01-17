package com.example.asus.myjournal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ChooseClassActivity extends AppCompatActivity {

    private EditText et;
    private LinearLayout linearLayout;
    private ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        buttons = new ArrayList<>();
        linearLayout = (LinearLayout)findViewById(R.id.group_linear_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setTitle("Добавить группу");
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
                    Button btn = new Button(getApplicationContext());
                    btn.setTextColor(Color.BLACK);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), JournalActivity.class);
                            startActivity(intent);
                        }
                    });
                    btn.setText(et.getText().toString());
                    linearLayout.addView(btn);
                    buttons.add(btn);
                }
            });
            builder.setNegativeButton("Закрыть", null);
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
