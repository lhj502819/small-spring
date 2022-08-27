package cn.onenine.springframework.test.bean;

/**
 * Description：老公类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 12:04
 */
public class Husband {

    Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

}
