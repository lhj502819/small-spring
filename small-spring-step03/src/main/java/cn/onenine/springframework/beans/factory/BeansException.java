package cn.onenine.springframework.beans.factory;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/3
 */
public class BeansException extends RuntimeException{

    private String message;

    private Exception e;

    public BeansException(String message,  Exception e) {
        super(message);
        this.e = e;
    }

    public BeansException(String message) {
        this.message = message;
    }
}
