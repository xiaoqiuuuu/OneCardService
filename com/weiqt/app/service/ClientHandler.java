package com.weiqt.app.service;

import com.weiqt.app.entity.User;
import com.weiqt.app.result.Result;
import com.weiqt.app.security.Encryption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received request: " + request);

                // 处理请求并返回结果
                String response = processRequest(request);

                // 发送响应给客户端
                out.println(response);
            }

        } catch (Exception e) {
            System.err.println("Client disconnected: " + clientSocket);
            try {
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // 关闭客户端连接
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String processRequest(String request) {
        // 字符串解密
        request = Encryption.decrypt(request);

        String[] parts = request.split(" ");

        for(String part : parts) {
            System.out.println(part);
        }

        if(parts[0].equals("recharge")) {
            User re = UserService.recharge(Integer.parseInt(parts[1]), Double.parseDouble(parts[2])).getData();
            if(re == null) {
                System.out.println("用户名不存在");
                return "False";
            } else {
                UserService.showUser();
                System.out.println("\n\n\n");
                // UserService.showRecord();
            }
        } else if(parts[0].equals("consume")) {
            User re = UserService.consume(Integer.parseInt(parts[1]), Double.parseDouble(parts[2])).getData();
            if(re == null) {
                System.out.println("用户名不存在");
                return "False";
            } else {
                UserService.showUser();
                // UserService.showRecord();
            }
        } else {
            return "Faile";
        }

        return "Success";
    }
}
