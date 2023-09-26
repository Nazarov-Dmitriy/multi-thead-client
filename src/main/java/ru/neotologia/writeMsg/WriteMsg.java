package ru.neotologia.writeMsg;

import ru.neotologia.clientSomthing.ClientSomthing;
import ru.neotologia.loger.Loger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class WriteMsg extends Thread {
    BufferedReader inputUser;
    BufferedWriter out;
    String nickname;

    public WriteMsg(BufferedReader inputUser, BufferedWriter out, String nickname) {
        this.inputUser = inputUser;
        this.out = out;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        while (true) {
            String userWord;
            try {
                userWord = inputUser.readLine();
                if (userWord.equals("/exit")) {
                    out.write("/exit");
                    out.flush();
                    Loger.write("INFO", "Пользователь завершил соединение");
                    ClientSomthing.downService();
                    break;
                } else {
                    out.write(nickname + ": " + userWord + "\n");
                    Loger.write("Message", userWord);
                    out.flush();
                }

            } catch (IOException e) {
                Loger.write("ERROR", " " + e.getMessage());
                ClientSomthing.downService();
            }
        }
    }
}