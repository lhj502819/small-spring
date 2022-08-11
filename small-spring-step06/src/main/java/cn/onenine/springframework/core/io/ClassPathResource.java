package cn.onenine.springframework.core.io;

import cn.hutool.core.lang.Assert;
import cn.onenine.springframework.utils.ClassUtils;

import java.io.InputStream;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:40
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    /**
     * 通过ClassLoader读取ClassPath下的文件信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public InputStream getInputStream() throws Exception {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new IllegalArgumentException("Could not find resource [" + path + "]");
        }
        return is;
    }
}
