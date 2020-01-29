package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main9Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);


        CardView cv = findViewById(R.id.cv1);
        CardView cv2 = findViewById(R.id.cv2);
        CardView cv3 = findViewById(R.id.cv3);
        LinearLayout ln1 = findViewById(R.id.ln1);
        LinearLayout ln2 = findViewById(R.id.ln2);
        LinearLayout ln3 = findViewById(R.id.ln3);
        ImageView im1 = findViewById(R.id.im1);
        ImageView im2 = findViewById(R.id.im2);
        ImageView im3 = findViewById(R.id.im3);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        cv.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
        ln1.setOnClickListener(this);
        ln2.setOnClickListener(this);
        ln3.setOnClickListener(this);
        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                Toast.makeText(this,"Type A",Toast.LENGTH_SHORT).show();
                Intent openTypeA1 = new Intent(this,Main11Activity.class);
                startActivity(openTypeA1);
                break;
            case R.id.ln1:
                Toast.makeText(this,"Type A",Toast.LENGTH_SHORT).show();
                Intent openTypeA2 = new Intent(this,Main11Activity.class);
                startActivity(openTypeA2);
                break;
            case R.id.im1:
                Toast.makeText(this,"Type A",Toast.LENGTH_SHORT).show();
                Intent openTypeA3 = new Intent(this,Main11Activity.class);
                startActivity(openTypeA3);
                break;
            case R.id.cv1:
                Toast.makeText(this,"Type A",Toast.LENGTH_SHORT).show();
                Intent openTypeA4 = new Intent(this,Main11Activity.class);
                startActivity(openTypeA4);
                break;
            case R.id.tv2:
                Toast.makeText(this,"Type B",Toast.LENGTH_SHORT).show();
                Intent openTypeB1 = new Intent(this,Main12Activity.class);
                startActivity(openTypeB1);
                break;
            case R.id.ln2:
                Toast.makeText(this,"Type B",Toast.LENGTH_SHORT).show();
                Intent openTypeB2 = new Intent(this,Main12Activity.class);
                startActivity(openTypeB2);
                break;
            case R.id.im2:
                Toast.makeText(this,"Type B",Toast.LENGTH_SHORT).show();
                Intent openTypeB3 = new Intent(this,Main12Activity.class);
                startActivity(openTypeB3);
                break;
            case R.id.cv2:
                Toast.makeText(this,"Type B",Toast.LENGTH_SHORT).show();
                Intent openTypeB4 = new Intent(this,Main12Activity.class);
                startActivity(openTypeB4);
                break;
            case R.id.tv3:
                Toast.makeText(this,"Type C",Toast.LENGTH_SHORT).show();
                Intent openTypeC1= new Intent(this,Main13Activity.class);
                startActivity(openTypeC1);
                break;
            case R.id.ln3:
                Toast.makeText(this,"Type C",Toast.LENGTH_SHORT).show();
                Intent openTypeC2 = new Intent(this,Main13Activity.class);
                startActivity(openTypeC2);
                break;
            case R.id.im3:
                Toast.makeText(this,"Type C",Toast.LENGTH_SHORT).show();
                Intent openTypeC3 = new Intent(this,Main13Activity.class);
                startActivity(openTypeC3);
                break;
            case R.id.cv3:
                Toast.makeText(this,"Type C",Toast.LENGTH_SHORT).show();
                Intent openTypeC4 = new Intent(this,Main13Activity.class);
                startActivity(openTypeC4);
                break;


        }

    }
}
