package cn.onenine.springframework.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/5
 */
public class UserDao {

    private static Map<String ,String> hashMap = new HashMap<>();

    static {
        hashMap.put("1001" , "壹玖");
        hashMap.put("1002" , "壹玖1");
        hashMap.put("1003" , "壹玖2");
    }

    public String queryUserName(String uId){
        return hashMap.get(uId);
    }

}