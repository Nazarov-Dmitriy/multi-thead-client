package ru.neotologia;

import ru.neotologia.clientSomthing.ClientSomthing;
import ru.neotologia.FileCreator.FileCreator;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        int port = Integer.parseInt(readSettings());
        String ipAddr = "localhost";
        FileCreator.create();
        new ClientSomthing(ipAddr, port);
    }

    public static String readSettings() {
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader("settings.txt"))) {
            str = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str.replaceAll("[^\\d]", "");
    }
}

