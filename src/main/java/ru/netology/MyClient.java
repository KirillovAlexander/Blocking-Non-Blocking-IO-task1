package ru.netology;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 23444);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
            String msg;
            while (true) {
                System.out.print("Введите номер члена ряда Фибоначчи: ");
                msg = scanner.nextLine();
                if ("end".equals(msg)) break;
                out.println(msg);
                System.out.println("Ждем вычисления значения сервером...");
                String serverMsg = in.readLine();
                System.out.println(serverMsg);
                inputBuffer.clear();
            }
        }
    }
}
