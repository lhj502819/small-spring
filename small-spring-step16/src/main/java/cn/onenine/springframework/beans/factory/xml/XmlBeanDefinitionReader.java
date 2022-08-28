package cn.onenine.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.PropertyValue;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.config.BeanReference;
import cn.onenine.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.onenine.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.onenine.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import cn.onenine.springframework.core.io.Resource;
import cn.onenine.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description：解析XML处理Bean注册
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:46
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | DocumentException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 根据输入流解析XML
     *
     * @param inputStream 输入流
     * @throws ClassNotFoundException
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException, ClassNotFoundException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        //解析context:component-scan标签，扫描包中的类并提取相关信息，用于组装BeanDefinition
        Element componentScan = root.element("component-scan");
        if (componentScan != null) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isBlank(scanPath)) {
                throw new BeansException("The value of base-package config cant not blank...");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");

        for (Element element : beanList) {

            //解析标签
            Element bean = (Element) element;
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            //读取init-method标签
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");

            //获取Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            //优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            if (StrUtil.isNotEmpty(initMethod)) {
                beanDefinition.setInitMethodName(initMethod);
            }

            if (StrUtil.isNotEmpty(destroyMethod)) {
                beanDefinition.setDestroyMethodName(destroyMethod);
            }

            //作用范围
            String scope = bean.attributeValue("scope");
            if (StrUtil.isNotEmpty(scope)) {
                beanDefinition.setScope(scope);
            }

            List<Element> propertyList = bean.elements("property");

            //读取属性并填充
            for (Element property : propertyList) {
                //解析标签： property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                //获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;

                //创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName [" + beanName + "] is not allowed");
            }

            //注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);

        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }

    @Override
    public void loadBeanDefinitions(Resource... resource) {
        for (Resource res : resource) {
            loadBeanDefinitions(res);
        }
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
}
