package cn.onenine.springframework.test.bean;

/**
 * Description：老婆类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 12:04
 */
public class Wife {

    private Husband husband;

    private IMother mother;

    public String queryHusband(){
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }



    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
