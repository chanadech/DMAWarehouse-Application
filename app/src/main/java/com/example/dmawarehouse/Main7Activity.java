package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._OrderDetail;
import com.example.dmawarehouse.DB_Objects._ProductCategory;

import org.w3c.dom.Text;

import java.util.List;

public class Main7Activity extends AppCompatActivity implements View.OnClickListener {

    private List<_ProductCategory> Catalogue;
    private TextView productList;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        productList = findViewById(R.id.textView33);
        refreshButton = findViewById(R.id.button4);

        refreshButton.setOnClickListener(this);
        updateList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                updateList();
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateList();
    }

    public void updateList(){
        DBHandler DBH = new DBHandler(this);
        Catalogue = DBH.get_ProductCategory();
        String listBuilder = "";
        for (_ProductCategory C: Catalogue) {
            listBuilder += "" + C.get_ID() + "\t\t\t\t\t\t\t\t" + C.get_NAME() + "\n";
        }
        productList.setText(listBuilder);
    }
}
