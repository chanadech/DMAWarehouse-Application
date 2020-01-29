package com.example.dmawarehouse.DB_Objects;

public class _OrderDetail {

    private int _OrderID;
    private int _ProductID;
    private int _Quantity;
    private String _DepositDate;
    private String _WithdrawalDate;
    private char _WarehouseType;
    private String _Username;

    public _OrderDetail(int _OrderID, int _ProductID, int _Quantity, String _DepositDate, String _WithdrawalDate, char _WarehouseType, String _Username){
        this._OrderID = _OrderID;
        this._ProductID = _ProductID;
        this._Quantity = _Quantity;
        this._DepositDate = _DepositDate;
        this._WithdrawalDate = _WithdrawalDate;
        this._WarehouseType = _WarehouseType;
        this._Username = _Username;
    }

    public void set_OrderID(int _OrderID) {
        this._OrderID = _OrderID;
    }
    public int get_OrderID(){
        return _OrderID;
    }

    public void set_ProductID(int _ProductID) {
        this._ProductID = _ProductID;
    }
    public int get_ProductID(){
        return _ProductID;
    }

    public void set_Quantity(int _Quantity) {
        this._Quantity = _Quantity;
    }
    public int get_Quantity(){
        return _Quantity;
    }

    public void set_DepositDate(String _DepositDate) {
        this._DepositDate = _DepositDate;
    }
    public String get_DepositDate(){
        return _DepositDate;
    }

    public void set_WithdrawalDate(String _WithdrawalDate) {this._WithdrawalDate = _WithdrawalDate; }
    public String get_WithdrawalDate(){
        return _WithdrawalDate;
    }

    public void set_WarehouseType(char _WarehouseType) {
        this._WarehouseType = _WarehouseType;
    }
    public char get_WarehouseType(){
        return _WarehouseType;
    }

    public void set_Username(String _Username) {
        this._Username = _Username;
    }
    public String get_Username(){
        return _Username;
    }
}
