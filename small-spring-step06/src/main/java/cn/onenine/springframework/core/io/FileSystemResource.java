package cn.onenine.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/9 21:43
 */
public class FileSystemResource implements Resource{

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        return new FileInputStream(file);
    }

    public final String getPath() {
        return this.path;
    }
}
