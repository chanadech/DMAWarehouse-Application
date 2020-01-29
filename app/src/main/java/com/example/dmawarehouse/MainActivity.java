package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Welcome to DMA Warehouse System Management",Toast.LENGTH_SHORT).show();

        button2 = (Button) findViewById(R.id.btt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

    }
    public void  openActivity3(){
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}
