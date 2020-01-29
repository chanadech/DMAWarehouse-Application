package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._OrderDetail;
import com.example.dmawarehouse.DB_Objects._ProductCategory;
import com.example.dmawarehouse.DB_Objects._UserRegister;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Main5Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView mTv;
    TextView mTv2;
    Button mBtn;
    Button mBtn2;
    Button Btn3;
    EditText eT1;
    EditText eT2;
    //private EditText depositLabel;
    //private EditText withdrawalLabel;

    private String username;

    Calendar c;
    Calendar c2;

    private int day,month,year;

    DatePickerDialog dpd;
    DatePickerDialog dpd2;

    //private static double money = 10000.0;
    private double money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mTv =(TextView)findViewById(R.id.textView21);
        mBtn =(Button)findViewById(R.id.editText3);
        mTv2 = (TextView)findViewById(R.id.textView22);
        mBtn2 =(Button)findViewById(R.id.editText4);
        Btn3 = (Button)findViewById(R.id.button6);
        eT1 = (EditText)findViewById(R.id.editText);
        eT2 = (EditText)findViewById(R.id.editText2);
        //depositLabel = (EditText)findViewById(R.id.editText3);
        //withdrawalLabel = (EditText)findViewById(R.id.editText4);

        username = getIntent().getStringExtra("username");
        DBHandler DBH = new DBHandler(this);
        money = DBH.get_UserRegister(username).get_credit();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);
                month++;

                Toast.makeText(Main5Activity.super.getBaseContext(),day + "/" + month + "/" + year,Toast.LENGTH_SHORT).show();


                dpd = new DatePickerDialog(Main5Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        mMonth++;
                        if(mYear < year){
                            Toast.makeText(Main5Activity.super.getBaseContext(),"Date unavailable. Please try again.",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(mMonth < month && year == mYear){
                            Toast.makeText(Main5Activity.super.getBaseContext(),"Date unavailable. Please try again.",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(mDay < day && month == mMonth && year == mYear){
                            Toast.makeText(Main5Activity.super.getBaseContext(),"Date unavailable. Please try again.",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mTv.setText(mDay+"/"+(mMonth)+"/"+mYear); }
                },year,month-1,day);

                //dpd.updateDate(year,month,day);
                dpd.show();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2 = Calendar.getInstance();
                day = c2.get(Calendar.DAY_OF_MONTH);
                month = c2.get(Calendar.MONTH);
                year = c2.get(Calendar.YEAR);
                month++;

                dpd2 = new DatePickerDialog(Main5Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int m2Year, int m2Month, int m2Day) {
                        m2Month++;
                        if(m2Year < year){
                            Toast.makeText(Main5Activity.super.getBaseContext(),"Date unavailable. Please try again.",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(m2Month < month && year == m2Year){
                            Toast.makeText(Main5Activity.super.getBaseContext(),"Date unavailable. Please try again.",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(m2Day < day && month == m2Month && year == m2Year){
                            Toast.makeText(Main5Activity.super.getBaseContext(),"Date unavailable. Please try again.",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mTv2.setText(m2Day+"/"+(m2Month)+"/"+m2Year); }
                },year,month-1,day);

                //dpd2.updateDate(year,month,day);
                dpd2.show();
            }
        });

        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String type = spinner.getSelectedItem().toString();
                    String[] depDay = (mTv.getText().toString()).split("/");
                    String[] witDay = (mTv2.getText().toString()).split("/");
                    int day1 = Integer.parseInt(depDay[0]);
                    int day2 = Integer.parseInt(witDay[0]);
                    int month1 = Integer.parseInt(depDay[1]);
                    int month2 = Integer.parseInt(witDay[1]);
                    int year1 = Integer.parseInt(depDay[2]);
                    int year2 = Integer.parseInt(witDay[2]);


                    if(year1 > year2) {
                        Toast.makeText(Main5Activity.super.getBaseContext(), "Invalid Date!", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (year1 == year2 && month1 > month2) {
                        Toast.makeText(Main5Activity.super.getBaseContext(), "Wrong input in month or day!", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (year1 == year2 && month1 == month2 && day1 >= day2) {
                        Toast.makeText(Main5Activity.super.getBaseContext(), "Invalid deposit date!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    Date firstDate = sdf.parse(day1 + "/" + month1 + "/" + year1);
                    Date secondDate = sdf.parse(day2 + "/" + month2 + "/" + year2);
                    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    long price;

                    if (!TextUtils.isEmpty(eT2.getText().toString())) {
                        Integer quantity = Integer.parseInt(eT2.getText().toString());
                        if (quantity > 0) {
                            if(type.equals("A")) {
                                price = (quantity * days * 10);//quantity * days * price

                            } else if(type.equals("B")) {
                                price = (quantity * days * 5);//quantity * days * price
                            } else {
                                price = (quantity * days);//quantity * days * price
                            }
                            if (money < price) {
                                Toast.makeText(Main5Activity.super.getBaseContext(), "Not enough money in your account! Require $" + price, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            money -= price;
                            Toast.makeText(Main5Activity.super.getBaseContext(), quantity + " of the item \""+ eT1.getText().toString() + "\" has been booked for deposit in warehouse " + type + " for " + days + " days.", Toast.LENGTH_LONG).show();
                            Toast.makeText(Main5Activity.super.getBaseContext(),  price + "$ paid successfully.", Toast.LENGTH_LONG).show();

                            DBHandler DBH = new DBHandler(Main5Activity.this);
                            DBH.update_UserRegister(new _UserRegister(username,null,null,money));

                            int latestID = DBH.getLatest_Order().get_OrderID();
                            int latestPID = DBH.getLatest_ProductCategory();
                            int searchIndex = DBH.isExist_ProductCategory(eT1.getText().toString());

                            if(searchIndex>=0)
                                DBH.set_OrderDetail(new _OrderDetail(latestID + 1, searchIndex, quantity,
                                        day1 + "/" + month1 + "/" + year1, day2 + "/" + month2 + "/" + year2,
                                        type.charAt(0), username));
                            else {
                                DBH.set_OrderDetail(new _OrderDetail(latestID + 1, latestPID + 1, quantity,
                                        day1 + "/" + month1 + "/" + year1, day2 + "/" + month2 + "/" + year2,
                                        type.charAt(0), username));
                                DBH.set_ProductCategory(new _ProductCategory(latestPID,eT1.getText().toString()));
                            }
                            finish();

                            /*
                            Toast toast1 = Toast.makeText(Main5Activity.super.getBaseContext(), "You have " + money + " left.", Toast.LENGTH_LONG);
                            toast1.setMargin(0,20);
                            toast1.show();
                             */

                        } else {
                            Toast.makeText(Main5Activity.super.getBaseContext(), "Enter the correct quantity!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Main5Activity.super.getBaseContext(), "The amount is empty!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Main5Activity.super.getBaseContext(),"Inputs are invalid! ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean isLeapYear(int year){
        if (year%4 == 0 || year%400 == 0) {
            return true;
        } else if (year%100 == 0) {
            return false;
        } else {
            return false;
        }
    }

}




