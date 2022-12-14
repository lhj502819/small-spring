package cn.onenine.springframework.test.bean;

import cn.onenine.springframework.BeanNameAware;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.*;
import cn.onenine.springframework.context.ApplicationContext;
import cn.onenine.springframework.context.ApplicationContextAware;
import cn.onenine.springframework.test.dao.UserDao;

/**
 * Description：用户服务
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/2
 */
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

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


    @Override
    public void setBeanName(String beanName) {
        System.out.println("BeanName：" + beanName);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoader：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
