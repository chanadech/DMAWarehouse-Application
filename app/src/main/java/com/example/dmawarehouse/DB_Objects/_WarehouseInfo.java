package com.example.dmawarehouse.DB_Objects;

public class _WarehouseInfo {
    private char _warehouseType;
    private double _warehousePrice;

    public _WarehouseInfo(char _warehouseType, double _warehousePrice){
        this._warehouseType = _warehouseType;
        this._warehousePrice = _warehousePrice;
    }

    public void set_warehouseType(char _warehouseType) {
        this._warehouseType = _warehouseType;
    }
    public char get_warehouseType(){
        return _warehouseType;
    }

    public void set_warehousePrice(double _warehousePrice) {
        this._warehousePrice = _warehousePrice;
    }
    public double get_warehousePrice(){
        return _warehousePrice;
    }
}
