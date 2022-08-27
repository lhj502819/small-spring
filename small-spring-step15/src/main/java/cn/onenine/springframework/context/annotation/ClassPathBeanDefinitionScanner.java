package cn.onenine.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.beans.factory.support.AutowiredAnnotationBeanPostProcessor;
import cn.onenine.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.onenine.springframework.stereotype.Component;

import java.util.Set;

/**
 * Description：扫描并注册指定包路径下的BeanDefinition
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/23 21:18
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     *
     * @param basePackages
     */
    public void doScan(String... basePackages){
        for (String basePackage : basePackages) {
            //获取所有标注Component的BeanDefinition
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidateComponents) {
                //解析Bean的作用域
                resolveBeanScope(beanDefinition);
                registry.registerBeanDefinition(determineBeanName(beanDefinition) , beanDefinition);
            }
        }

        registry.registerBeanDefinition("cn.onenine.springframework.beans.factory.support.AutowiredAnnotationBeanPostProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 获取Bean的名称，如果未设置则将类名称的首字母小写作为BeanName
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        return StrUtil.isBlank(value) ?
                StrUtil.lowerFirst(beanClass.getSimpleName()) : value ;
    }

    /**
     * 设置Bean的Scope
     */
    private void resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        //如果标注了scope
        if(scope != null ){
            //设置作用域
            beanDefinition.setScope(scope.value());
        }
    }
}
