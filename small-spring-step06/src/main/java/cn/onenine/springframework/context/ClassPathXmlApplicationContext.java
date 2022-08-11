package cn.onenine.springframework.context;

/**
 * Description：应用上下文实现类
 *  是具体提供给用户使用的应用上下文方法
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/8/11 22:45
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[]{configLocations});
    }



    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
