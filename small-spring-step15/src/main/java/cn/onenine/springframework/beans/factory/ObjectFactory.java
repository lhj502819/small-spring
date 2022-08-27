package cn.onenine.springframework.beans.factory;

import cn.onenine.springframework.beans.BeansException;

/**
 * Description： Defines a factory which can return an Object instance
 *  (possibly shared or independent) when invoked.
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/27 10:51
 */
public interface ObjectFactory<T>{
    T getObject () throws BeansException;
}
