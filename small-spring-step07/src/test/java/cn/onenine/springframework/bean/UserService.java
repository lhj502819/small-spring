package cn.onenine.springframework.bean;

import cn.onenine.springframework.beans.factory.DisposableBean;
import cn.onenine.springframework.beans.factory.InitializingBean;
import cn.onenine.springframework.dao.UserDao;

/**
 * Description：用户服务
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class UserService implements InitializingBean, DisposableBean {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行销毁方法.....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet.....");
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息：" + userDao.queryUserName(uId) +"," + company + "," + location);
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


}
