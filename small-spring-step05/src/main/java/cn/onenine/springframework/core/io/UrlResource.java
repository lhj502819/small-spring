package cn.onenine.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:43
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not open InputStream for [" + this.url + "]", e);
        }
    }
}
