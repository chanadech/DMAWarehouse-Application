package com.example.dmawarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmawarehouse.DB_Objects.DBHandler;
import com.example.dmawarehouse.DB_Objects._UserRegister;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button credit_label;
    private _UserRegister currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        credit_label = findViewById(R.id.button3);
        TextView user_label = findViewById(R.id.userTextView);
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        ImageView textView7 = findViewById(R.id.textView7);
        ImageView imageView = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView imageView5 = findViewById(R.id.imageView5);
        ImageView imageView6 = findViewById(R.id.imageView6);
        TextView textView10 = findViewById(R.id.textView10);

        //user_label.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        textView10.setOnClickListener(this);

        DBHandler DBH = new DBHandler(this);
        currentUser = DBH.get_UserRegister(getIntent().getStringExtra("username"));
        user_label.setText(currentUser.get_username());
        String credit_amount = "Credits: " + currentUser.get_credit() + "$";
        credit_label.setText(credit_amount);
    }

    @Override
    public void onResume(){
        super.onResume();
        DBHandler DBH = new DBHandler(this);
        currentUser = DBH.get_UserRegister(getIntent().getStringExtra("username"));
        //Toast.makeText(this,"Called resume!",Toast.LENGTH_SHORT).show();
        String credit_amount = "Credits: " + currentUser.get_credit() + "$";
        credit_label.setText(credit_amount);
    }


    public  void  onClick(View view){
        switch (view.getId()){
            case R.id.textView:
                Toast.makeText(this,"Deposit",Toast.LENGTH_SHORT).show();
                Intent openDeposit = new Intent(this,Main5Activity.class);
                openDeposit.putExtra("username",currentUser.get_username());
                startActivity(openDeposit);
                break;
            case R.id.textView2:
                Toast.makeText(this,"Withdraw",Toast.LENGTH_SHORT).show();
                Intent openWithdraw = new Intent(this,Main6Activity.class);
                openWithdraw.putExtra("username",currentUser.get_username());
                startActivity(openWithdraw);
                break;
            case R.id.textView3:
                Toast.makeText(this,"View Product",Toast.LENGTH_SHORT).show();
                Intent openViewProduct = new Intent(this,Main7Activity.class);
                startActivity(openViewProduct);
                break;
            case R.id.textView4:
                Toast.makeText(this,"Add Money",Toast.LENGTH_SHORT).show();
                Intent openAddMoney = new Intent(this,Main8Activity.class);
                openAddMoney.putExtra("username",currentUser.get_username());
                startActivity(openAddMoney);
                break;
            case R.id.textView5:
                Toast.makeText(this,"Warehouse Type",Toast.LENGTH_SHORT).show();
                Intent openWdMoney = new Intent(this,Main9Activity.class);
                startActivity(openWdMoney);
                break;
            case R.id.textView6:
                Toast.makeText(this,"Contact Us",Toast.LENGTH_SHORT).show();
                Intent openContact = new Intent(this,Main10Activity.class);
                startActivity(openContact);
                break;
            case R.id.textView7:
                Toast.makeText(this,"Log out",Toast.LENGTH_SHORT).show();
                Intent logOut = new Intent(this,MainActivity.class);
                startActivity(logOut);
                break;
            case R.id.imageView1:
                Toast.makeText(this,"Deposit Product",Toast.LENGTH_SHORT).show();
                Intent depositPd = new Intent(this,Main5Activity.class);
                depositPd.putExtra("username",currentUser.get_username());
                startActivity(depositPd);
                break;
            case R.id.imageView2:
                Toast.makeText(this,"Withdraw",Toast.LENGTH_SHORT).show();
                Intent openWithdraw2 = new Intent(this,Main6Activity.class);
                openWithdraw2.putExtra("username",currentUser.get_username());
                startActivity(openWithdraw2);
                break;
            case R.id.imageView3:
                Toast.makeText(this,"View Product",Toast.LENGTH_SHORT).show();
                Intent openViewProduct2 = new Intent(this,Main7Activity.class);
                startActivity(openViewProduct2);
                break;
            case R.id.imageView4:
                Toast.makeText(this,"Add Money",Toast.LENGTH_SHORT).show();
                Intent openAddMoney2= new Intent(this,Main8Activity.class);
                openAddMoney2.putExtra("username",currentUser.get_username());
                startActivity(openAddMoney2);
                break;
            case R.id.imageView5:
                Toast.makeText(this,"Warehouse Type",Toast.LENGTH_SHORT).show();
                Intent openWdMoney2 = new Intent(this,Main9Activity.class);
                startActivity(openWdMoney2);
                break;
            case R.id.imageView6:
                Toast.makeText(this,"Contact Us",Toast.LENGTH_SHORT).show();
                Intent openContact2 = new Intent(this,Main10Activity.class);
                startActivity(openContact2);
                break;
            case R.id.textView10:
                Toast.makeText(this,"Log out",Toast.LENGTH_SHORT).show();
                Intent openWindow= new Intent(this,MainActivity.class);
                startActivity(openWindow);
                break;

        }
    }
}







