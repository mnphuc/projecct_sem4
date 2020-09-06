package com.project.sem4.model.map;

import com.project.sem4.model.view.CartInfo;
import lombok.Data;

import java.util.HashMap;
@Data
public class HashMapCart {
    private HashMap<Long, CartInfo> cartInfoHashMap;
}
