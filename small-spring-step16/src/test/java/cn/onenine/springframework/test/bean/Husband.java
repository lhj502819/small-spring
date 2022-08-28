package cn.onenine.springframework.test.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Description：老公类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 12:04
 */
public class Husband {

    private String wifeName;

    private LocalDate marriageDate;

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "wifeName='" + wifeName + '\'' +
                ", marriageDate=" + marriageDate +
                '}';
    }
}
