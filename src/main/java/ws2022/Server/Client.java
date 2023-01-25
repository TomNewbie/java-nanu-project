package ws2022.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.application.Platform;
import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;
import ws2022.Client.ViewController.BoardGameController;
import ws2022.Client.ViewController.BoardGameOnlController;
import ws2022.Client.ViewController.EnterProfileOnlController;
import ws2022.Client.ViewController.SceneController;
import ws2022.Middleware.API;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(String ipv4) {
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
        if (data.length == 1) {
            EnterProfileOnlController epoc = EnterProfileOnlController.getInstance();
            epoc.setStatus();
            return;
        }
        // else this will sent the name and age of second player
        GameManager.PLAYER2 = new Player(data[1], Integer.parseInt(data[2]));
    }

    private void handleMessage(String s) throws IOException {
        API.Type type = API.getTypeFromClient(s);
        switch (type) {
            case ENTER_PROFILE:
                onReceiveEnterProfile(s);
                break;
            case DATA:
                onReceiveData(s);
                break;
            case ROLL_DICE:
                onReceiveRollDice(s);
            case ANSWER:
                onReceiveAnswer(s);
                // break;
                // case GUESS_PICTURE:
                // break;
        }
        System.out.println("hehe handle message");
    }

    public void onReceiveAnswer(String s) {
        // suy nghĩ thêm cái này
        String status = s.split(";")[1];
        if (status.equals("right")) {

        } else {
            GameManager.changeTurn();
        }
    }

    public void onReceiveRollDice(String s) throws IOException {
        String result = s.split(";")[1];
        // if player 2 turn print message that player 2 get
        BoardGameController bgc = BoardGameController.getInstance();
        if (!GameManager.isPlayer1Turn) {
            bgc.message.setText("Player " + GameManager.PLAYER2.getName() + " get: " + result);
            return;
        }
        GameManager.COLOR = result;
        bgc.clickRollDice();
    }

    public void requestDice() {
        sendMessage("", API.Type.ROLL_DICE);
    }

    public void sendAnswer(String answer) {
        sendMessage(answer, API.Type.ANSWER);
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
    public void setUpGame(String s) {
        String splString[] = s.split(";");
        System.out.println(splString.length);
        for (int i = 2; i < splString.length - 1; i = i + 2) {
            GameManager.myList.add(new Disc(splString[i + 1], splString[i]));
        }
        GameManager.pictureName = GameManager.getArrayValue();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SceneController sc = SceneController.getInstance();
                    sc.enterGameOnline(GameManager.stage);
                } catch (Exception e) {
                    System.out.println("can not load game");
                    e.printStackTrace();
                    // TODO: handle exception
                }
            }
        });
    }

    public void onReceiveData(String s) {
        String type = s.split(";")[1];
        System.out.println(s);
        if (type.equals("boardgame")) {
            setUpGame(s);
        } else if (type.equals("turn")) {
            String data = s.split(";")[2];
            if (GameManager.PLAYER1.getName().equals(data)) {
                GameManager.isPlayer1Turn = true;
            } else {
                GameManager.isPlayer1Turn = false;
            }
        } else {
            System.out.println("cover run");
            BoardGameController bgc = BoardGameController.getInstance();
            bgc.coverCoords = getCoord(s);
        }
        // } else if (type.equals("cover")) {
        // GameManager.coverHashMap
        // }

    }

    public Coordinate[] getCoord(String s) {
        Coordinate[] result = new Coordinate[5];
        String[] splString = s.split(";");
        int count = 0;
        for (int i = 2; i < splString.length - 1; i = i + 2) {
            if (count > 4) {
                break;
            }
            result[count] = new Coordinate(Integer.parseInt(splString[i]), Integer.parseInt(splString[i + 1]));
            count++;
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(result[i].getRow());
        }
        return result;
    }

    public void sendMessage(String s, API.Type type) {
        try {
            String sendMessage = type.toString() + ";"
                    + GameManager.PLAYER1.getName() + ";" + s;
            bufferedWriter.write(sendMessage);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("Send to server");
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
                        e.printStackTrace();
                        break;
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
