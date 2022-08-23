package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.PropertyValue;
import cn.onenine.springframework.beans.PropertyValues;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.onenine.springframework.core.io.DefaultResourceLoader;
import cn.onenine.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * Description：解析Bean属性占位符，通过实现{@link  BeanFactoryPostProcessor}，在完成BeanDefinition加载之后，对Bean的属性进行修改
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/23 22:13
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 占位符前缀
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * 占位符后缀
     */
    public static final String DEFAULT_PLACEHOLDER_suffix = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        //需要获取到配置的属性value为${xx}这种格式的属性
        try {
            //加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            //转换为Properties
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int endIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_suffix);
                    //判断是否包含了 ${}
                    if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                        String propertyKey = strVal.substring(startIndex + 2, endIndex);
                        String propertyVal = properties.getProperty(propertyKey);
                        buffer.replace(startIndex , endIndex + 1 , propertyVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyKey, buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException(" PropertyPlaceholderConfigurer load error..", e);
        }
    }

    /**
     * 配置的文件
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
