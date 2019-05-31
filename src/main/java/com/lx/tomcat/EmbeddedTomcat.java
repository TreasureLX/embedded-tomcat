package com.lx.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * @author lanxing
 */
public class EmbeddedTomcat {
    public static void main(String[] args) throws LifecycleException, InterruptedException, ServletException {
        String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        System.out.println(classPath);
        //声明tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        //设置host
        Host host = tomcat.getHost();
        host.setName("localhost");
        host.setAppBase("webapps");
        String webapp = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "webapp";
        Context context = tomcat.addWebapp("/", webapp);
        if (context instanceof StandardContext) {
            StandardContext standardContext = (StandardContext) context;
            standardContext.setDefaultWebXml(classPath + File.separator + "conf" + File.separator + "web.xml");
        }
//        host.addChild(context);
        tomcat.start();
        //设置ClassPath

        //wait......
        synchronized (tomcat) {
            tomcat.wait();
        }
    }
}
