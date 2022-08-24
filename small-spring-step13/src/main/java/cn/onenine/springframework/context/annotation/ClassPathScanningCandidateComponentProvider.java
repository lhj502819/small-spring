package cn.onenine.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import cn.onenine.springframework.beans.factory.config.BeanDefinition;
import cn.onenine.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Description：扫描指定包路径下标注{@link  Component}注解的Class并组装其BeanDefinition
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/23 21:14
 */
public class ClassPathScanningCandidateComponentProvider {

    protected Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);

        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
