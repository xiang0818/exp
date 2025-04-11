package cn.think.in.java.open.exp.plugin.depend;

import cn.think.in.java.open.exp.client.*;

/**
 * @Author cxs
 **/
public abstract class AbstractBoot implements ExpBoot {

    private final ClassLoader pluginClassLoader;
    private final String classLocation;
    private final DefaultScanner pluginBeanRegister;

    public AbstractBoot() {
        if (getClass().getClassLoader() instanceof PluginClassLoader) {
            this.classLocation = ((PluginClassLoader) getClass().getClassLoader()).getPath();
        } else {
            throw new RuntimeException("classLocation 非法");
        }
        this.pluginClassLoader = getClass().getClassLoader();
        this.pluginBeanRegister = new DefaultScanner();
        this.pluginBeanRegister.setPluginClassLoader(this.pluginClassLoader);
        this.pluginBeanRegister.setLocation(this.classLocation);
        String scanPath = getScanPath();
        if (StringUtil.isEmpty(scanPath)) {
            throw new RuntimeException("AbstractBoot getScanPath 不能为空.");
        }
        this.pluginBeanRegister.setScanPath(getScanPath());
    }

    @Override
    public PluginObjectScanner getRegister() throws Throwable {
        return this.pluginBeanRegister;
    }

    protected String getScanPath() {
        return getClass().getPackage().getName();
    }

}
