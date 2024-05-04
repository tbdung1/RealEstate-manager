package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");
    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static Map<String, String> type(){
        Map<String,String> listType = new HashMap<>();
        for(TransactionType item : TransactionType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
