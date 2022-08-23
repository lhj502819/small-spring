package cn.onenine.springframework.test.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/5
 */
public class UserDao {

    private static Map<String ,String> hashMap = new HashMap<>();

    private void initData(){
        hashMap.put("10001" , "壹玖");
        hashMap.put("10002" , "壹玖1");
        hashMap.put("10003" , "壹玖2");
    }

    public String queryUserName(String uId){
        return hashMap.get(uId);
    }

}