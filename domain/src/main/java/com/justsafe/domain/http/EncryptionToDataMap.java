package com.justsafe.domain.http;



import com.justsafe.libarch.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


public class EncryptionToDataMap {
/*    public static String Encryption(Map<String, Object> map, String time) {
        Object[] objects = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            //android 7
            objects = new Object[0];
            objects = map.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue()).toArray();
            LogUtils.i("==android 7==Encryption==: " + objects.length);

        } else {
            //android 6
            objects = new Object[map.size()];
            int count = 0;
            for (String item : map.keySet()) {
                LogUtils.i("==android 6==Encryption==item: " + item + "  value:" + map.get(item).toString());
                objects[count++] = item + "=" + map.get(item).toString();
            }
        }
        String concat = Joiner.on("&").join(Arrays.asList(objects)).concat("&curDate=" + time);
        LogUtils.i(concat);
        return concat;
    }*/


/*    public static String Encryption_Back(Map<String, Object> map, String time) {
        return Joiner.on("&").join(Arrays.asList(map.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue()).toArray())).concat("&curDate=" + time);
    }*/

    public static String getDetaString() {
//  Api26 to return this    String date=  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
