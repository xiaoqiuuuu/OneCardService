package com.weiqt.app.service;


import com.weiqt.app.database.util.DataBase;
import com.weiqt.app.entity.Record;
import com.weiqt.app.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OneCarService {
    private static final int THREAD_POOL_SIZE = 10; // 设置线程池大小

    public OneCarService(int port) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // 创建固定大小的线程池

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // 为每个客户端连接创建一个新的线程来处理
                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

