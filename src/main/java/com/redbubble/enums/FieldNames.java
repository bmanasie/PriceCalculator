package com.redbubble;

public enum FieldNames {

    BASEPRICE("base-price"),
    QUANTITY("quantity"),
    OPTIONS("options") ,
    ARTIST("artist-markup"),
    PRODUCT("product-type");


    private String name;

    FieldNames(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
