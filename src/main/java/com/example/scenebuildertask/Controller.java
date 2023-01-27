package com.example.scenebuildertask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField serverIPField;

    @FXML
    private TextField serverPortField;

    @FXML
    private TextField messageField;

    @FXML
    private TextArea outputArea;

    @FXML
    public void handleSendButtonAction() {
        String serverIP = serverIPField.getText();
        int serverPort = Integer.parseInt(serverPortField.getText());
        String message = messageField.getText();

        try (
                Socket socket = new Socket(serverIP, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println(message);
            String response = in.readLine();
            outputArea.appendText("Server: " + response + "\n");
        } catch (IOException e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }
}
