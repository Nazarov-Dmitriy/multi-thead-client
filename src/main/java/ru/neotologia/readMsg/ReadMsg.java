package ru.neotologia.readMsg;

import ru.neotologia.clientSomthing.ClientSomthing;
import ru.neotologia.loger.Loger;

import java.io.BufferedReader;
import java.io.IOException;


public class ReadMsg extends Thread {
    BufferedReader in;

    public ReadMsg(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        String str;
        try {
            while (true) {
                str = in.readLine();
                System.out.println(str);
            }
        } catch (IOException e) {
            Loger.write("ERROR", " " + e.getMessage());
        }
    }
}
