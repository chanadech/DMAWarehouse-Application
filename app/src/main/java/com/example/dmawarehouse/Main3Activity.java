package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._UserRegister;

import java.util.List;

public class Main3Activity extends AppCompatActivity implements OnClickListener {
    private EditText eT1,eT2;
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
        setContentView(R.layout.activity_main3);
        eT1 = (EditText)findViewById(R.id.editText);
        eT2 = (EditText)findViewById(R.id.editText2);
        eT1.setFilters(new InputFilter[] { filter });
        eT2.setFilters(new InputFilter[] { filter });
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    public  void  onClick(View view){
        switch (view.getId()) {
            case R.id.button8:
                //Toast.makeText(this,"Sign In",Toast.LENGTH_SHORT).show();
                Intent openPage4 = new Intent(this, Main4Activity.class);

                DBHandler DBH = new DBHandler(this);
                //_UserRegister User;
                if (DBH.login_UserRegister(new _UserRegister(eT1.getText().toString(), eT2.getText().toString(), null, 0)) != null){
                    openPage4.putExtra("username", eT1.getText().toString());
                    startActivity(openPage4);
                }
                else{
                    eT1.setText("");
                    eT2.setText("");
                    Toast.makeText(this,"Incorrect Credentials, Try Again!",Toast.LENGTH_SHORT).show();
                    //eT1.setHint("Username or Password Incorrect!");
                }

                break;
            case R.id.button9:
                Toast.makeText(this,"Sign Up",Toast.LENGTH_SHORT).show();
                Intent openPage2 = new Intent(this,ActivityPage2.class);
                startActivity(openPage2);
                break;
        }
    }
}
