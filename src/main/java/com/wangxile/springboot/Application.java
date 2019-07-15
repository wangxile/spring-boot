package com.wangxile.springboot;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.AsyncContext;
import java.io.File;

public class Application {

    //启动tomcat
    public static void run() {
        Tomcat tomcat = new Tomcat();
        //设置端口
        tomcat.setPort(9091);
        //设置你要发布的项目
        try {
            String sourcePath = Application.class.getResource("/").getPath();
            //设置webapp路径
            Context ctx = tomcat.addWebapp("",new File("src/main/webapp").getAbsolutePath());
            //设置项目的class文件目录
            WebResourceRoot resourceRoot = new StandardRoot(ctx);
            resourceRoot.addPreResources(new DirResourceSet(resourceRoot, "/WEB-INF/classes", sourcePath, "/ "));
            ctx.setResources(resourceRoot);
            tomcat.start();
            tomcat.getServer().await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
