package com.example.dmawarehouse.DB_Objects;

public class _ProductCategory {
    private int _ID;
    private String _NAME;

    public _ProductCategory(int _ID, String _NAME){
        this._ID = _ID;
        this._NAME = _NAME;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }
    public int get_ID(){
        return _ID;
    }

    public void set_NAME(String _NAME) {
        this._NAME = _NAME;
    }
    public String get_NAME(){
        return _NAME;
    }
}
