package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._OrderDetail;

import java.util.List;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener {

    private String username;
    private TextView itemPopulation;
    private EditText queryOrder;
    private Button withdrawButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        queryOrder = findViewById(R.id.editText10);
        withdrawButton = findViewById(R.id.button7);
        itemPopulation = findViewById(R.id.textView33);

        queryOrder.setOnClickListener(this);
        withdrawButton.setOnClickListener(this);

        username = getIntent().getStringExtra("username");
        updateList();
    }

    @Override
    public void onClick(View view) {
        _OrderDetail Order = null;
        switch (view.getId()) {
            case R.id.button7:
                DBHandler DBH = new DBHandler(this);

                if(queryOrder.getText().toString().equals(""))
                    Toast.makeText(this, "Withdrawal failed. Order ID cannot be left blank.", Toast.LENGTH_LONG).show();

                else {
                    Order = DBH.delete_OrderDetail(Integer.parseInt(queryOrder.getText().toString()));
                    Toast.makeText(this, "Withdrawal order initiated by the customer was completed.", Toast.LENGTH_LONG).show();
                }

                updateList();
                break;
        }
    }

    public void updateList(){
        DBHandler DBH = new DBHandler(this);
        List<_OrderDetail> allOrder = DBH.get_OrderDetail(username);
        String listBuilder = "";
        for (_OrderDetail O : allOrder) {
            listBuilder += "ID:" + O.get_OrderID() + "[" + O.get_ProductID() + "],\t Quantity:" + O.get_Quantity()
                    + " @Warehouse " + O.get_WarehouseType() + "\n Date: " + O.get_DepositDate() + " - " + O.get_WithdrawalDate() + "\n\n";
        }
        itemPopulation.setText(listBuilder);
    }
}
