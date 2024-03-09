package com.weiqt.app.service;

import com.weiqt.app.database.util.DataBase;
import com.weiqt.app.entity.User;
import com.weiqt.app.entity.Record;
import com.weiqt.app.result.Result;

import java.util.Date;

public class UserService {
    private static DataBase<User> userDatabase;
    private static DataBase<Record> recordDatabase;
    public UserService(DataBase<User> userDatabase, DataBase<Record> recordDatabase) {
        this.userDatabase = userDatabase;
        this.recordDatabase = recordDatabase;
    }

    public static Result<User> recharge(Integer id, double amount) {
        User user = userDatabase.findById(id).getData();
        if(user != null) {
            user.setBalance(user.getBalance() + amount);
            user.setUpdateTime(new Date());
            if(userDatabase.update(user).getCode() != 200) {
                return Result.failure("数据库修改失败");
            }
        } else {
            return Result.failure("用户不存在");
        }

        // 将记录写入数据
        recordDatabase.add(new Record(id, 1, amount , user.getBalance()));

        return Result.success(user);
    }

    public static Result<User> consume(Integer id, double amount) {
        User user = userDatabase.findById(id).getData();
        if(user != null) {
            if(user.getBalance() < amount) {
                return Result.failure("余额不足");
            }
            user.setBalance(user.getBalance() - amount);
            user.setUpdateTime(new Date());
            if(userDatabase.update(user).getCode() != 200) {
                return Result.failure("数据库修改失败");
            }
        } else {
            return Result.failure("用户不存在");
        }
        // 将记录写入数据
        recordDatabase.add(new Record(id, 0, amount , user.getBalance()));

        return Result.success(user);
    }

    public static Result<User> addUser(String name, String password, String card, String cardPassword, Double balance) {
        User user = new User(null, name, password, card, cardPassword);
        user.setBalance(balance);
        if(userDatabase.add(user).getCode() != 200) {
            return Result.failure("数据库添加失败");
        }
        return Result.success(user);
    }
    public static void showUser() {
        for(User item : userDatabase.findAll().getData()) {
            System.out.println(item);
        }
    }
    public static void showRecord() {
        for(Record item : recordDatabase.findAll().getData()) {
            System.out.println(item);
        }
    }
}
