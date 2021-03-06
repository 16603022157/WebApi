package com.admin.tool;

import java.util.ArrayList;
import java.util.List;

public class JudgementRole {

    public static boolean Distinguishroles() {
        boolean flag=false;
        String policecode = CacheUtils.get("PoliceCode").toString();
        int manager = Integer.parseInt(CacheUtils.get("manager").toString());
        if (manager == 0) {
            flag = false;
        } else if (manager == 1) {
            flag = true;
        }else{
            flag=false;
        }
        return flag;
    }
//        Object Role = CacheUtils.get("Role");
//        Object accountname = CacheUtils.get("accountname");
//        System.out.println(Role.toString());
//        if (Role.toString().equals("民警")) {
//            type = 1;
//        } else if (Role.toString().equals("法制管理员")) {
//            type = 2;
//        } else if (Role.toString().equals("所长")) {
//            type = 3;
//        } else if (Role.toString().equals("平台管理员")) {
//            type = 4;
//        }
//        return type;


    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n; //(先计算出余数)
        int number = source.size() / n; //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

}
