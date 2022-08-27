package cn.onenine.springframework.beans;

/**
 * Description：属性值
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/5
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
