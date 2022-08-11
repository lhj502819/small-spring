package cn.onenine.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description：默认资源加载器
 *   实现过程简单，但这也是设计模式约定具体结果，像这里不会让外部调用方知道过多的细节，而是仅关心具体调用结果即可
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/10
 */
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"Location must not be null");

        //判断是否为ClassPath、URL以及文件
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            }catch (MalformedURLException e){
                return new FileSystemResource(location);
            }
        }
    }
}
