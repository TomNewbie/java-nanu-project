package ws2022.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import ws2022.Client.Model.Player;
import ws2022.Middleware.API;
import ws2022.Middleware.GameManager;
import ws2022.Middleware.API.Type;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int clientNumber;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            // in java 2 types of Stream : Character and Byte
            // Character -> end with Writer and Byte -> end with Stream
            // We want to convert byte to character
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientNumber = clientHandlers.size();
            clientHandlers.add(this);
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            // TODO: handle exception

        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                System.out.println(messageFromClient);
                handleMessage(messageFromClient);
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
                break;
                // TODO: handle exception
            }
        }
    }

    public void handleMessage(String s) throws IOException {
        API.Type type = API.getTypeFromClient(s);
        switch (type) {
            case ENTER_PROFILE:
                System.out.println("hehe");
                handleEnterProfile(s);
                break;
            case CHOOSE_COLOR:
                break;
            case GUESS_PICTURE:
                break;

        }
        System.out.println("handle Message");
    }

    public void handleEnterProfile(String s) throws NumberFormatException {
        String[] splStrings = s.split(";");

        if (clientHandlers.size() == 1 && GameManager.PLAYER1 == null) {
            System.out.println("helo");
            GameManager.PLAYER1 = new Player(splStrings[1], Integer.parseInt(splStrings[2]));
            String msg = Type.ENTER_PROFILE.toString();
            unicastMessage(msg, 0);
            System.out.println("helo");
            return;
        }
        GameManager.PLAYER2 = new Player(splStrings[1], Integer.parseInt(splStrings[2]));
        String msgToPlayer1 = Type.ENTER_PROFILE.toString() + ";"
                + GameManager.PLAYER2.getName() + ";" + GameManager.PLAYER2.getAge();
        String msgToPlayer2 = Type.ENTER_PROFILE.toString() + ";"
                + GameManager.PLAYER1.getName() + ";" + GameManager.PLAYER1.getAge();
        unicastMessage(msgToPlayer1, 0);
        unicastMessage(msgToPlayer2, 1);
    }

    public void unicastMessage(String messageFromClient, int clientNumber) {
        System.out.println("messageFromClient");
        try {
            clientHandlers.get(clientNumber).bufferedWriter.write(messageFromClient);
            clientHandlers.get(clientNumber).bufferedWriter.newLine();
            clientHandlers.get(clientNumber).bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            // TODO: handle exception
        }
    }

    public void broadcastMessage(String messageFromClient) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.bufferedWriter.write(messageFromClient);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                // TODO: handle exception
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: client num" + clientNumber + " has left the chat!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }
}
