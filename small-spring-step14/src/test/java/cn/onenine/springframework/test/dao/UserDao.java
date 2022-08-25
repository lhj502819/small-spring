package cn.onenine.springframework.test.dao;

import cn.onenine.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/24 23:12
 */
@Component
public class UserDao implements IUserDao{
    private static Map<String,String > hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "壹玖1，北京1");
        hashMap.put("10002", "壹玖2，北京2");
        hashMap.put("10003", "壹玖3，北京3");
        hashMap.put("10004", "壹玖4，北京4");
    }

    public  String queryUserName(String uId){
        return hashMap.get(uId);
    }
}
