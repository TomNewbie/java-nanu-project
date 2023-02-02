package ws2022.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import ws2022.Client.Model.GameManager;
import javafx.application.Platform;
import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;
import ws2022.Client.ViewController.BoardGameController;
import ws2022.Client.ViewController.BoardGameOnlController;
import ws2022.Client.ViewController.EnterProfileOnlController;
import ws2022.Client.ViewController.SceneController;
import ws2022.Client.ViewController.SoundController;
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
                break;
            case ANSWER:
                onReceiveAnswer(s);
                break;
            case POP_UP:
                onReceivePopUp(s);
                break;
            case CHOOSE_COVER:
                onReceiveChooseCover(s);
                break;
            case END_GAME:
                onReceiveEndGame(s);
                // case GUESS_PICTURE:
                // break;
        }
        System.out.println("hehe handle message");
    }

    public void onReceiveEndGame(String s) {
        updateScore(s);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                SceneController sc = SceneController.getInstance();
                try {
                    sc.loadSceneByStage(GameManager.stage, "Leaderboard");
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
            }
        });
    }

    public void onReceiveChooseCover(String s) {
        String[] splStrings = s.split(";");
        int column = Integer.parseInt(splStrings[1]);
        int row = Integer.parseInt(splStrings[2]);
        GameManager.COLOR = splStrings[3];
        System.out.println("onReceiveChooseCover");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                BoardGameController bgc = BoardGameController.getInstance();
                String coverImage = "/ws2022/assets/Covers/" + GameManager.COLOR + ".png";
                try {
                    if (GameManager.isPlayer1Turn) {
                        bgc.message.setVisible(false);
                    } else {
                        bgc.message.setText("Waiting player " + GameManager.PLAYER2.getName() + " roll dice");
                    }
                    bgc.deleteCover();
                    bgc.putCover(coverImage, new Coordinate(column, row), GameManager.COLOR);
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
            }
        });
    }

    public void onReceiveAnswer(String s) throws IOException {
        // suy nghĩ thêm cái này
        String[] splString = s.split(";");
        String status = splString[1];
        String answer = splString[2];
        SceneController sc = SceneController.getInstance();
        BoardGameController bgc = BoardGameController.getInstance();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if (status.equals("right")) {
                        GameManager.answer = answer;
                        if (GameManager.isPlayer1Turn) {
                            SoundController sound = new SoundController();
                            sound.correctAnswer();
                            sc.loadSceneByStage(bgc.popUpStage, "RightAnswer");
                        } else {
                            bgc.message.setText(
                                    "Player " + GameManager.PLAYER2.getName() + " guess " + answer + " on dice "
                                            + GameManager.COLOR + " got Right answer");
                        }
                    } else {
                        GameManager.answer = splString[3];
                        GameManager.imageString = splString[4];
                        if (GameManager.isPlayer1Turn) {
                            SoundController sound = new SoundController();
                            sound.wrongAnswer();
                            sc.loadSceneByStage(bgc.popUpStage, "WrongAnswer");
                        } else {
                            bgc.message.setText(
                                    "Player " + GameManager.PLAYER2.getName() + " guess " + answer + " on dice "
                                            + GameManager.COLOR + " got Wrong answer");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // public void onReceiveStatus(String s) throws IOException {
    // String[] splString = s.split(";");
    // String typeString = splString[2];
    // if (typeString.equals("closePopUp"))
    // onReceivePopUp(s);

    // }

    public void onReceivePopUp(String s) throws IOException {
        String[] splString = s.split(";");
        String status = splString[1];
        BoardGameController bgc = BoardGameController.getInstance();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if (status.equals("wrong")) {
                        if (GameManager.isPlayer1Turn) {

                            bgc.removeGuessPictureBtn();
                            bgc.dice.setVisible(false);
                        } else {
                            // GameManager.changeTurn();
                            // bgc.message.setVisible(false);
                            // bgc.setTurn(GameManager.isPlayer1Turn);
                            bgc.createRollDiceBtn();
                        }
                        GameManager.changeTurn();
                        bgc.setTurn(GameManager.isPlayer1Turn);
                    } else {
                        updateScore(s);
                        bgc.update();
                        if (GameManager.isPlayer1Turn) {
                            bgc.removeGuessPictureBtn();
                            bgc.message.setVisible(true);
                            bgc.message.setText(
                                    "Please choose picture to place " + GameManager.COLOR + " cover");
                        } else {
                            bgc.message.setText(
                                    "Waiting for player " + GameManager.PLAYER2.getName() + " choose picture to cover "
                                            + GameManager.COLOR);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
            }
        });
    }

    public void updateScore(String s) {
        String[] splStrings = s.split(";");
        if (GameManager.PLAYER1.getName().equals(splStrings[2])) {
            GameManager.PLAYER1.addScore(Integer.parseInt(splStrings[3]));
            GameManager.PLAYER2.addScore(Integer.parseInt(splStrings[5]));
        } else {
            GameManager.PLAYER1.addScore(Integer.parseInt(splStrings[5]));
            GameManager.PLAYER2.addScore(Integer.parseInt(splStrings[3]));
        }
    }

    public void onReceiveRollDice(String s) throws IOException {
        String result = s.split(";")[1];
        // if player 2 turn print message that player 2 get
        BoardGameController bgc = BoardGameController.getInstance();
        GameManager.COLOR = result;
        if (!GameManager.isPlayer1Turn) {
            bgc.message.setText("Player " + GameManager.PLAYER2.getName() + " get: " + result);
            return;
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    bgc.clickRollDice();
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
            }
        });

    }

    public void requestDice() {
        sendMessage("", API.Type.ROLL_DICE);
    }

    public void sendAnswer(String answer) {
        sendMessage(answer, API.Type.ANSWER);
    }

    public void closePopUp(String s) {
        sendMessage(s, API.Type.POP_UP);
    }

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
            if (count >= Dice.numDice) {
                break;
            }
            result[count] = new Coordinate(Integer.parseInt(splString[i]), Integer.parseInt(splString[i + 1]));
            count++;
        }
        for (int i = 0; i < Dice.numDice; i++) {
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
                        System.out.println(msgFromServer);
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
