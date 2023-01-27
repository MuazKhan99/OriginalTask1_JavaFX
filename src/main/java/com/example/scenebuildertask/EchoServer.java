package com.example.scenebuildertask;

import java.io.*;
import java.net.*;

public class EchoServer {
    public EchoServer() {

    }

    public void establish() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 1234");
            System.exit(-1);
        }
        while(true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: 1234");
                System.exit(-1);
            }
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
            } catch (IOException ioe) {
                System.out.println("Failed in creating streams");
                System.exit(-1);
            }
            String inputLine, outputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);
                    if (inputLine.equals("Bye.")) {
                        clientSocket.close();
                        break;
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Failed in reading, writing");
                System.exit(-1);
            }
        }
    }
}
