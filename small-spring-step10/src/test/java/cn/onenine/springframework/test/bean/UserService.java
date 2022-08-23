package cn.onenine.springframework.test.bean;

import cn.onenine.springframework.test.dao.IUserDao;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description：用户服务
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class UserService implements IUserService {

    private String uId;
    private String company;
    private String location;
    /**
     * 新修改了一个原有UserDao属性为IUserDao，后面会给这个属性注入代理对象
     */
    private IUserDao userDao;

    @Override
    public String  queryUserInfo(){
        try {
            TimeUnit.MILLISECONDS.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "壹玖，10001，北京";
    }

    public String register(String userName){
        try {
            TimeUnit.MILLISECONDS.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " 成功";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

}
