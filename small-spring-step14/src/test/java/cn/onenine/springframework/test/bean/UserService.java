package cn.onenine.springframework.test.bean;

import cn.onenine.springframework.beans.factory.annotation.Autowired;
import cn.onenine.springframework.beans.factory.annotation.Value;
import cn.onenine.springframework.stereotype.Component;
import cn.onenine.springframework.context.annotation.Scope;
import cn.onenine.springframework.test.dao.IUserDao;
import cn.onenine.springframework.test.dao.UserDao;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description：用户服务
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
@Component("userService")
public class UserService implements IUserService {
    @Value("${token}")
    private String token;
    /**
     * 新修改了一个原有UserDao属性为IUserDao，后面会给这个属性注入代理对象
     */
    @Autowired
    private IUserDao userDao;

    @Override
    public String queryUserInfo() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，token：" + token;
    }

    public String register(String userName) {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " 成功";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
