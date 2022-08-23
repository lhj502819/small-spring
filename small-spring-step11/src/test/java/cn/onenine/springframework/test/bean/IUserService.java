package cn.onenine.springframework.test.bean;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/20 20:58
 */
public interface IUserService {
    String queryUserInfo();

    String register(String userName);
}
