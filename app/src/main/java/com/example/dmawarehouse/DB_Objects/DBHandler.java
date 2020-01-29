package com.example.dmawarehouse.DB_Objects;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static String DB_NAME = "Dummy_DB.sqlite3";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase SQLiteDB;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
        this.openDataBase();
        //this.close();
        //Log.d("Path","<Path>"+DB_PATH);
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        SQLiteDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        return SQLiteDB != null;
    }


    @Override
    public synchronized void close() {
        if (SQLiteDB != null)
            SQLiteDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public List<_OrderDetail> get_OrderDetail(String currentUser) {
        SQLiteDB = this.getReadableDatabase();
        List<_OrderDetail> ODList = new ArrayList<_OrderDetail>();

        Cursor cursor = SQLiteDB.query("OrderDetailTable", null,
                "username = ?", new String[]{currentUser}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            ODList.add(
                    new _OrderDetail(
                            Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),
                            Integer.parseInt(cursor.getString(2)),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5).charAt(0),
                            cursor.getString(6)));
            cursor.moveToNext();
        }

        
        SQLiteDB.close();
        return ODList;
    }

    public _OrderDetail delete_OrderDetail(int orderID) {
        SQLiteDB = this.getWritableDatabase();
        _OrderDetail withdrawOrder = null;

        Cursor cursor = SQLiteDB.query("OrderDetailTable", null,
                "order_ID = ?", new String[]{String.valueOf(orderID)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            if(cursor.getString(0).equals(String.valueOf(orderID)))
                withdrawOrder = new _OrderDetail(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5).charAt(0),
                        cursor.getString(6));
            cursor.moveToNext();
        }

        
        SQLiteDB.delete("OrderDetailTable","order_ID = " + orderID,null);
        SQLiteDB.close();
        return withdrawOrder;
    }

    public List<_ProductCategory> get_ProductCategory() {
        SQLiteDB = this.getReadableDatabase();
        List<_ProductCategory> PCList = new ArrayList<_ProductCategory>();

        Cursor cursor = SQLiteDB.query("ProductInfoTable", null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            PCList.add(
                    new _ProductCategory(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1)));

            cursor.moveToNext();
        }

        
        SQLiteDB.close();
        return PCList;
    }

    public int getLatest_ProductCategory() {
        SQLiteDB = this.getReadableDatabase();
        List<_ProductCategory> PCList = new ArrayList<_ProductCategory>();

        Cursor cursor = SQLiteDB.query("ProductInfoTable", null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            PCList.add(
                    new _ProductCategory(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1)));

            cursor.moveToNext();
        }

        
        SQLiteDB.close();
        return PCList.size() - 1;
    }

    public int isExist_ProductCategory(String productName) {
        SQLiteDB = this.getReadableDatabase();

        Cursor cursor = SQLiteDB.query("ProductInfoTable", null,
                "product_Name = ?", new String[]{productName}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            if(cursor.getString(1).equals(productName)) {
                
                SQLiteDB.close();
                return cursor.getInt(0);
            }
            cursor.moveToNext();
        }

        
        SQLiteDB.close();
        return -1;
    }

    public _UserRegister login_UserRegister(_UserRegister User) {
        SQLiteDB = this.getReadableDatabase();
        //_UserRegister attemptUser = new _UserRegister(null,null,null,null);

        Cursor cursor = SQLiteDB.query("UserRegisterTable", null,
                "username = ? AND password = ?", new String[]{User.get_username(),User.get_password()}, null, null, null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                if (User.get_username().equals(cursor.getString(0)) && User.get_password().equals(cursor.getString(1))) {
                    
                    SQLiteDB.close();
                    return new _UserRegister(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3));
                }
            }
        }

        
        SQLiteDB.close();
        return null;
    }

    public _UserRegister get_UserRegister(String queryUser) {
        SQLiteDB = this.getReadableDatabase();
        //_UserRegister attemptUser = new _UserRegister(null,null,null,null);

        Cursor cursor = SQLiteDB.query("UserRegisterTable", null,
                "username = ?", new String[]{queryUser}, null, null, null);

        if (cursor != null) {
            if(cursor.moveToFirst()){
                if(queryUser.equals(cursor.getString(0))) {
                    
                    SQLiteDB.close();
                    return new _UserRegister(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3));
                }
            }
        }

        
        SQLiteDB.close();
        return null;
    }

    public List<_WarehouseInfo> get_WarehouseInfo() {
        SQLiteDB = this.getReadableDatabase();
        List<_WarehouseInfo> WIList = new ArrayList<_WarehouseInfo>();

        Cursor cursor = SQLiteDB.query("WarehouseTable", null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            WIList.add(
                    new _WarehouseInfo(
                            cursor.getString(0).charAt(0),
                            cursor.getDouble(1)));

            cursor.moveToNext();
        }

        
        SQLiteDB.close();
        return WIList;
    }

    public void set_ProductCategory(_ProductCategory Product) {
        SQLiteDB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put("product_ID", String.valueOf(Product.get_ID()));
        CV.put("product_Name", String.valueOf(Product.get_NAME()));

        SQLiteDB.insert("ProductInfoTable", null, CV);

        SQLiteDB.close();
    }

    public void set_OrderDetail(_OrderDetail Order) {
        SQLiteDB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.put("order_ID", String.valueOf(Order.get_OrderID()));
        CV.put("product_ID", String.valueOf(Order.get_ProductID()));
        CV.put("product_Quantity", String.valueOf(Order.get_Quantity()));
        CV.put("deposit_Date", Order.get_DepositDate());
        CV.put("withdrawal_Date", Order.get_WithdrawalDate());
        CV.put("warehouse_Type", String.valueOf(Order.get_WarehouseType()));
        CV.put("username", Order.get_Username());

        SQLiteDB.insert("OrderDetailTable", null, CV);

        SQLiteDB.close();
    }

    public _OrderDetail getLatest_Order() {
        SQLiteDB = this.getReadableDatabase();
        List<_OrderDetail> ODList = new ArrayList<_OrderDetail>();

        Cursor cursor = SQLiteDB.query("OrderDetailTable", null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            ODList.add(
                    new _OrderDetail(
                            Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),
                            Integer.parseInt(cursor.getString(2)),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5).charAt(0),
                            cursor.getString(6)));
            cursor.moveToNext();
        }

        
        SQLiteDB.close();
        if(ODList.size()==0)
            return new _OrderDetail(-1,-1,0,null,null,' ',null);
        return ODList.get(ODList.size() - 1);
    }

    public boolean set_UserRegister(_UserRegister User) {
        SQLiteDB = this.getWritableDatabase();

        /*
        Cursor cursor = SQLiteDB.query("UserRegisterTable", null,
                "username = ?", new String[]{User.get_username()}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if(cursor.getString(0).equals(User.get_username()))
                return false;
        }

         */
        //Username is a unique key.

        ContentValues CV = new ContentValues();
        CV.put("username", User.get_username());
        CV.put("password", User.get_password());
        CV.put("email", User.get_email());
        CV.put("credit", User.get_credit());

        boolean Result = SQLiteDB.insert("UserRegisterTable", null, CV) > 0;

        SQLiteDB.close();
        return Result;
    }

    public void update_UserRegister(_UserRegister User) {
        _UserRegister deprecatedUser = this.get_UserRegister(User.get_username());
        SQLiteDB = getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("username", User.get_username());
        CV.put("password", deprecatedUser.get_password());
        CV.put("email", deprecatedUser.get_email());
        CV.put("credit", User.get_credit());

        SQLiteDB.update("UserRegisterTable", CV, "username='"+User.get_username()+"'",null);

        SQLiteDB.close();
    }
}