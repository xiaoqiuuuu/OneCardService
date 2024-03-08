package com.weiqt.app.config;

/**
 *
 * 该类是本服务器的配置类
 * 设置用户名，数据库，端口等功能
 *
 */

public class ServiceConfig {
    public static final String CONFIG_FILE = "server.properties";
    public static final int DEFAULT_PORT = 12306;

    private int port;
    private String databaseHost;
    private String databaseName;
    private String databaseUser;
    private String databasePassword;



}
