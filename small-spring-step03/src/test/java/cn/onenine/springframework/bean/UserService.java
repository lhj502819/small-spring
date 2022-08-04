package cn.onenine.springframework.bean;

import org.junit.BeforeClass;

/**
 * Description：用户服务
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class UserService {

    private String name;

    public UserService(){
        name = "无名氏";
    }

    public UserService(String name) {
        this.name = name;
    }

    public String queryUserInfo() {
        return "查询用户信息为 : " + name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(name);
        return sb.toString();
    }
}
