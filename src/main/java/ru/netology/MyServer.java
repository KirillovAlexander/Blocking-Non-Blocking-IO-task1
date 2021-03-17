package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(23444);
        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    try {
                        int number = Integer.parseInt(line);
                        out.println("Ваше значение: " + calculate(number));
                    } catch (Exception ex) {
                        out.println("Вы ввели неверное значение!");
                        ex.printStackTrace(System.out);
                    }
                }
            }
        }
    }

    private static long calculate(int number) {
        long first_value = 0;
        long second_value = 1;
        long buffer = 0;
        for (int i = 1; i <= number; i++) {
            buffer = first_value + second_value;
            first_value = second_value;
            second_value = buffer;
        }
        return buffer;
    }
}
