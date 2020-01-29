package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._UserRegister;

public class Main8Activity extends AppCompatActivity {

    private double money;
    private Button bt11, bt13;
    private EditText bt12;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        username = getIntent().getStringExtra("username");

        final DBHandler DBH = new DBHandler(this);
        money = DBH.get_UserRegister(username).get_credit();
        bt11 = findViewById(R.id.button11);
        bt11.setText(String.valueOf(money));
        bt12 = (EditText) findViewById(R.id.button12);
        bt13 = (Button) findViewById(R.id.button13);

        bt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!TextUtils.isEmpty(bt12.getText().toString())) {
                        Double addAmount = Double.parseDouble(bt12.getText().toString());

                        if (addAmount > 0) {
                            money += addAmount;
                            bt11.setText(String.valueOf(money));
                            DBH.update_UserRegister(new _UserRegister(username,null,null,money));
                            Toast.makeText(Main8Activity.super.getBaseContext(), addAmount.toString() + " has been added!", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(Main8Activity.super.getBaseContext(), "Enter the correct value!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Main8Activity.super.getBaseContext(), "The amount is empty!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Main8Activity.super.getBaseContext(),"Input must be numbers!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
