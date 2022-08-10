package cn.onenine.springframework.core.io;

import cn.hutool.core.lang.Assert;

/**
 * Description：默认资源加载器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/10
 */
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"Location must not be null");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else {
            
        }

        return null;
    }
}
