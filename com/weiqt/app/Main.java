package com.weiqt.app;

import com.weiqt.app.config.ServiceConfig;
import com.weiqt.app.database.util.DataBase;
import com.weiqt.app.entity.User;
import com.weiqt.app.entity.Record;
import com.weiqt.app.service.OneCarService;
import com.weiqt.app.service.UserService;

import java.security.Provider;

public class Main {
    private static UserService userService;
    public static void main(String[] args) {

        // 加载配置文件
        ServiceConfig config = new ServiceConfig();

        // 加载数据库
        DataBase<User> userDatabase = new DataBase<>(config.getDefaultUserDatabaseName());
        DataBase<Record> recordDatabase = new DataBase<>(config.getDefaultRecordDatabaseName());

        // 加载数据服务
        userService = new UserService(userDatabase , recordDatabase);

        // UserService.addUser("weiqt", "123456", "11111", "11111", 10000.0);
        // UserService.addUser("Liubr", "123456", "22222", "11111", 10000.0);


        // 启动服务
        new OneCarService(config.getDefaultPort());

    }
}
