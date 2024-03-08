package com.weiqt.app.database.util;
import com.weiqt.app.result.Result;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * 该类实现了一个数据库的基类
 * 实现了数据的加载，增删改查等功能
 * 具体数据格式需要子类实现
 * 注意：子类需要考虑数据的持久化机制，以保证数据不会丢失。
 */

public abstract class DataBase<T extends Identifiable> {
    private List<T> data = Collections.synchronizedList(new CopyOnWriteArrayList<>());
    private String fileName;

    // 考虑多线程请求，需要对数据上锁
    private final Lock lock = new ReentrantLock();

    public DataBase(List<T> data, String fileName) {
        this.data = data;
        this.fileName = fileName;
    }
    public DataBase(String fileName) {
        this.fileName = fileName;
    }

    // 实现异步加载数据
    public CompletableFuture<Boolean> loadDataAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                lock.lock();
                try {
                    loadData();
                } finally {
                    lock.unlock();
                }
                return true;
            } catch (Exception e) {
                System.err.println("数据加载失败: " + e.getMessage());
                return false;
            } finally {
                lock.unlock();
            }
        });
    }

    protected abstract void loadData();

    public Result<T> add(T data) {
        lock.lock();
        try {
            for (T item : this.data) {
                if(item.getId() == data.getId()) {
                    return Result.failure("数据添加失败，ID 重复");
                }
            }
            this.data.add(data);
            return Result.success(data);
        } catch (Exception e) {
            return Result.failure("数据添加失败: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public Result<T> update(T data) {
        lock.lock();
        try {
            boolean isUpdate = false;
            for(int i = 0 ; i < this.data.size() ; i ++ ) {
                if(this.data.get(i).getId() == data.getId()) {
                    this.data.set(i, data);
                    isUpdate = true;
                    break;
                }
            }
            if(!isUpdate) {
                return Result.failure("更新数据失败，要修改的对象不存在");
            } else {
                return Result.success(data);
            }
        } catch (Exception e) {
            return Result.failure("更新数据失败: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public Result<T> delete(T data) {
        lock.lock();
        try {
            boolean isDelete = false;
            for(int i = 0 ; i < this.data.size() ; i ++ ) {
                if(this.data.get(i).getId() == data.getId()) {
                    this.data.remove(i);
                    isDelete = true;
                    break;
                }
            }
            if(!isDelete) {
                return Result.failure("数据删除失败，要删除的对象不存在");
            } else {
                return Result.success(data);
            }
        } catch (Exception e) {
            return Result.failure("数据删除失败: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public Result<List<T>> findAll() {
        try {
            lock.lock();
            return Result.success(data);
        } catch (Exception e) {
            return Result.failure("数据查询失败: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public Result<T> findById(Integer id) {
        try {
            lock.lock();
            for(T item : data) {
                if(item.getId() == id) {
                    return Result.success(item);
                }
            }
            return Result.failure("数据查询失败，要查询的对象不存在");
        } catch(Exception e) {
            return Result.failure("数据查询失败: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
