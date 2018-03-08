package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String uid;
    public  String number;
    public  String name;
    public  String business;
    public  String address;
    public  String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     *
     * @param uid the unique identifier
     * @param number a phone number
     * @param name the business name
     * @param business the primary business of the business
     * @param address the address of the business
     * @param province the province of the business
     */
    public Business(String uid, String number, String name, String business, String address, String province) {
        this.uid = uid;
        this.number = number;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }


    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("number", number);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
