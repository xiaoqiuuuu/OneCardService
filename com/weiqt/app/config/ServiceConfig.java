package com.weiqt.app.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * 该类是本服务器的配置类
 * 设置数据库名称，端口等功能
 *
 */

public class ServiceConfig {
    private static String CONFIG_FILE = "server.properties";
    private static Integer DEFAULT_PORT = 9959;
    private static String DEFAULT_USER_DATABASE_NAME = "Users.data";
    private static String DEFAULT_RECORD_DATABASE_NAME = "Records.data";
    private static String DEFAULT_USER_DATABASE_PATH = "";

    public void saveConfig() {
        Properties props = new Properties();
        // 将当前的配置信息保存到 Properties 对象中
        props.setProperty("port", String.valueOf(DEFAULT_PORT));
        props.setProperty("userDatabaseName", DEFAULT_USER_DATABASE_NAME);
        props.setProperty("recordDatabaseName", DEFAULT_RECORD_DATABASE_NAME);
        props.setProperty("userDatabasePath", DEFAULT_USER_DATABASE_PATH);

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            // 保存 Properties 到配置文件
            props.store(output, "Service Configurations");
        } catch (IOException e) {
            System.err.println("无法保存配置到文件: " + CONFIG_FILE);
            e.printStackTrace();
        }
    }
    public ServiceConfig() {
        saveConfig();
    }
    public Integer getDefaultPort() {
        return DEFAULT_PORT;
    }
    public String getDefaultUserDatabaseName() {
        return DEFAULT_USER_DATABASE_PATH + DEFAULT_USER_DATABASE_NAME;
    }
    public String getDefaultRecordDatabaseName() {
        return DEFAULT_USER_DATABASE_PATH + DEFAULT_RECORD_DATABASE_NAME;
    }

    public static String getConfigFile() {
        return CONFIG_FILE;
    }

    public static void setConfigFile(String configFile) {
        CONFIG_FILE = configFile;
    }

    public static void setDefaultPort(Integer defaultPort) {
        DEFAULT_PORT = defaultPort;
    }

    public static void setDefaultUserDatabaseName(String defaultUserDatabaseName) {
        DEFAULT_USER_DATABASE_NAME = defaultUserDatabaseName;
    }

    public static void setDefaultRecordDatabaseName(String defaultRecordDatabaseName) {
        DEFAULT_RECORD_DATABASE_NAME = defaultRecordDatabaseName;
    }

    public static String getDefaultUserDatabasePath() {
        return DEFAULT_USER_DATABASE_PATH;
    }

    public static void setDefaultUserDatabasePath(String defaultUserDatabasePath) {
        DEFAULT_USER_DATABASE_PATH = defaultUserDatabasePath;
    }

}
