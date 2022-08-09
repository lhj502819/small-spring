package cn.onenine.springframework.core.io;

import java.io.InputStream;

/**
 * Description：主要用于处理资源加载流
 *  定义Resource接口，提供获取流的方法，再分别实现三种不同的流文件操作：classPath、FileSystem、URL
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:36
 */
public interface Resource {

    InputStream getInputStream() throws Exception;

}
