package ru.neotologia.clientSomthing;

import ru.neotologia.loger.Loger;
import ru.neotologia.readMsg.ReadMsg;
import ru.neotologia.writeMsg.WriteMsg;

import java.io.*;
import java.net.Socket;


public class ClientSomthing {
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static BufferedReader inputUser;
    private String nickname;

    public ClientSomthing(String addr, int port) {
        try {
            socket = new Socket(addr, port);
            Loger.write("INFO", "Новое подключение");
        } catch (IOException e) {
            Loger.write("ERROR", " " + e.getMessage());
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            Loger.write("INFO", "Пользователь присоединился к чату");
            new ReadMsg(in).start();
            new WriteMsg(inputUser, out, nickname).start();
        } catch (IOException e) {
            downService();
        }
    }


    private void pressNickname() {
        System.out.println("Введите nickname");
        try {
            nickname = inputUser.readLine();
            out.write(nickname + "\n");
            out.flush();
        } catch (IOException e) {
            Loger.write("ERROR", " " + e.getMessage());

        }
    }

    public static void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                inputUser.close();
            }
        } catch (IOException e) {
            Loger.write("ERROR", " " + e.getMessage());
        }
    }
}