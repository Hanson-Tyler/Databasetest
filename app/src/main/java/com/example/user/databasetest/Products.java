package com.example.user.databasetest;



public class Products {

    //call this _id android uses this exact name a lot
    private int _id;
    private String _productname;
    //can add more variables as needed

    public Products(String productname){
        this._productname = productname;
    }

    public int get_id() {
        return _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }
}
