package com.justsafe.domain.http;


import com.justsafe.domain.api.JustRestService;

import java.util.HashMap;
import java.util.Map;

public class MyHeadMapUtil {
    private static MyHeadMapUtil instance;

    public static MyHeadMapUtil getInstance() {
        if (instance == null) {
            instance = new MyHeadMapUtil();
        }
        return instance;
    }

    public Map DataToHeader(Map datamap) {
        Map<String, Object> mheadMap = new HashMap<>();
        String time = EncryptionToDataMap.getDetaString();
        String headstr = "";
//        String headstr = RSAUtils.encrypt(JustRestService.PUCLIC_KEY, EncryptionToDataMap.Encryption(datamap, time));
        mheadMap.put("Authorization", "RSA" + " " + headstr);
        mheadMap.put("RequestDate", time);
        return mheadMap;
    }

}
