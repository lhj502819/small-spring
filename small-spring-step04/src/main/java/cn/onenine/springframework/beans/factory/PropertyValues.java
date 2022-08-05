package cn.onenine.springframework.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：属性集合，属性可能会有很多，所以还需要定义一个集合包装下
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/5
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 获取属性值
     * @param propertyName 属性名称
     */
    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : propertyValueList) {
            if(pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }
    
}
