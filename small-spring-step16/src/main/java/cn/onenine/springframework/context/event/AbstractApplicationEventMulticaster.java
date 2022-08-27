package cn.onenine.springframework.context.event;

import cn.onenine.springframework.beans.BeansException;
import cn.onenine.springframework.beans.factory.BeanFactory;
import cn.onenine.springframework.beans.factory.BeanFactoryAware;
import cn.onenine.springframework.context.ApplicationEvent;
import cn.onenine.springframework.context.ApplicationListener;
import cn.onenine.springframework.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Description：抽象事件广播器，对事件广播器的公用方法提取，在这个类中可以实现一些基本功能，避免所有直接实现接口方还需要处理细节。
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/17 21:13
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationLister(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationLister(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取符合广播事件中的监听处理器，具体的过滤动作在supportsEvent方法中
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();

        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if(supportsEvent(applicationListener,event)){
                allListeners.add(applicationListener);
            }
        }
        return allListeners;
    }

    /**
     * 判断监听器是否对该事件感兴趣 ， 主要包括对Cglib、Simple不通实例化需要获取目标Class，
     *  Cglib代理类需要获取父类的Class，普通类不需要
     * @param applicationListener
     * @param event
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        //按照CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy不通的实例化类型，需要判断后获取目标Class
        Class<?> targetClass = ClassUtils.isCglibSubClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;

        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();

        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        }catch (ClassNotFoundException e){
            throw new BeansException("wrong event class name：" + className);
        }

        //判断这个eventClassName对象所表示是的类或者接口与指定的event.getClass()参数所表示的类或者接口是否相同，或者是否是其父类或者父接口

        return eventClassName.isAssignableFrom(event.getClass());
    }


}
