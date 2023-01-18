package ws2022.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

import ws2022.Client.Model.Player;
import ws2022.Client.ViewController.SceneController;
import ws2022.Client.ViewController.WaitingController;
import ws2022.Middleware.API;
import ws2022.Middleware.GameManager;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(String ipv4, String username, String age) {
        try {
            this.socket = new Socket(ipv4, 1809);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            // TODO: handle exception
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void onReceiveEnterProfile(String s) throws IOException {
        String[] data = s.split(";");
        String status = data[1];
        if (status.equals("success")) {
            WaitingController wc = WaitingController.getInstance();
            wc.setName(GameManager.players.get(0).getName(), 1);
            SceneController sc = SceneController.getInstance();
            sc.enterWaiting(GameManager.stage);
        }
    }

    private void handleMessage(String s) throws IOException {
        API.Type type = API.getTypeFromClient(s);
        switch (type) {
            case ENTER_PROFILE:
                onReceiveEnterProfile(s);
                break;
            // break;
            // case CHOOSE_COLOR:
            // break;
            // case GUESS_PICTURE:
            // break;
        }
    }
    // public void sendMessage() {
    // try {
    // bufferedWriter.write(GameManager.PLAYER1.getName() + ";" +
    // GameManager.PLAYER1.getAge());
    // bufferedWriter.newLine();
    // bufferedWriter.flush();
    // Scanner scanner = new Scanner(System.in);
    // while (socket.isConnected()) {
    // String messageToSend = scanner.nextLine();
    // bufferedWriter.write(GameManager.PLAYER1.getName() + ": " + messageToSend);
    // bufferedWriter.newLine();
    // bufferedWriter.flush();
    // }
    // } catch (IOException e) {
    // closeEverything(socket, bufferedReader, bufferedWriter);
    // // TODO: handle exception
    // }
    // }

    public void sendMessage(String s, API.Type type) {
        try {
            String sendMessage = type.toString() + ";"
                    + GameManager.players.get(0).getName() + ";" + s;
            bufferedWriter.write(sendMessage);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            System.out.println("Some thing wrong with sendMessage");
            e.printStackTrace();
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromServer;
                while (socket.isConnected()) {
                    try {
                        msgFromServer = bufferedReader.readLine();
                        handleMessage(msgFromServer);
                    } catch (Exception e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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

    // public static void main(String[] args) throws IOException {
    // Scanner scanner = new Scanner(System.in);
    // System.out.println("Enter your username for the group chat: ");
    // String username = scanner.nextLine();
    // System.out.println("Please enter the server IP add");
    // String ipv4 = scanner.nextLine();
    // Socket socket = new Socket(ipv4, 8080);
    // Client client = new Client(socket, username);
    // client.listenForMessage();
    // client.sendMessage();
    // }

    public static void connectServer(String username, String age, String ipv4) throws IOException {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter your username for the group chat: ");
        // String username = scanner.nextLine();
        // System.out.println("Please enter the server IP add");
        // String ipv4 = scanner.nextLine();
        // Socket socket = new Socket(ipv4, 8080);
        // Client client = new Client(socket, username, age);
        // client.listenForMessage();
        // client.sendMessage();
    }
}
