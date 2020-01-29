package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputFilter;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._UserRegister;

public class ActivityPage2 extends AppCompatActivity {

    private Button button6,btn1;
    private EditText eT1, eT2, eT3,eT4;
    private String blockCharacterSet = "~#^|$%&*!+()=";
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        eT1 = (EditText)findViewById(R.id.editText);
        eT2 = (EditText)findViewById(R.id.editText2);
        eT3 = (EditText)findViewById(R.id.editText3);
        eT4 = (EditText)findViewById(R.id.editText4);
        eT1.setFilters(new InputFilter[] { filter });
        eT2.setFilters(new InputFilter[] { filter });
        eT3.setFilters(new InputFilter[] { filter });
        eT4.setFilters(new InputFilter[] { filter });
        btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!TextUtils.isEmpty(eT1.getText().toString()) && !TextUtils.isEmpty(eT4.getText().toString()) && !TextUtils.isEmpty(eT3.getText().toString()) && !TextUtils.isEmpty(eT4.getText().toString())) {
                        if (eT2.getText().toString().length() >= 6 && eT2.getText().toString().length() <= 16) {
                            if(eT2.getText().toString().equals(eT3.getText().toString())) {

                                DBHandler DBH = new DBHandler(ActivityPage2.this);
                                boolean isSuccess = DBH.set_UserRegister(new _UserRegister(eT1.getText().toString(),eT2.getText().toString(),eT4.getText().toString(),0));

                                if(isSuccess)
                                    Toast.makeText(ActivityPage2.super.getBaseContext(),eT1.getText().toString() + " has been registered.",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(ActivityPage2.super.getBaseContext(),"Registration Failed!",Toast.LENGTH_SHORT).show();

                                openActivity3();
                            } else {
                                Toast.makeText(ActivityPage2.super.getBaseContext(),"Password and Confirm password must be the same!",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityPage2.super.getBaseContext(),"Password must be 6 - 16 characters! " ,Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(ActivityPage2.super.getBaseContext(),"Inputs are invalid!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
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