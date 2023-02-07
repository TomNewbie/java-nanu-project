package ws2022.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;
import ws2022.Client.ViewController.SceneController;
import ws2022.Middleware.API;
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
                if (messageFromClient != null) {
                    System.out.println(messageFromClient);
                    handleMessage(messageFromClient);
                }
                System.out.println(messageFromClient);
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
                break;
                // TODO: handle exception
            }
        }
        closeEverything(socket, bufferedReader, bufferedWriter);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SceneController sc = SceneController.getInstance();
                    sc.showAlertMessage(Alert.AlertType.WARNING, "Connection fail", "Player " +
                            (clientNumber + 1) + " has disconnected");
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
            }
        });
    }

    public void handleMessage(String s) throws IOException {
        API.Type type = API.getTypeFromClient(s);
        switch (type) {
            case ENTER_PROFILE:
                // System.out.println("hehe");
                handleEnterProfile(s);
                break;
            case ROLL_DICE:
                handleRolldice();
                break;
            case ANSWER:
                handleAnswer(s);
                break;
            case POP_UP:
                handlePopUp(s);
                break;
            case CHOOSE_COVER:
                handleChooseCover(s);
                break;
            case SET_COLOR:
                handleSetColor(s);
                break;
            case CLOSE_CONNECTION:
                handleCloseConnection();
                break;
            default:
                System.out.println("Unspecify type");
                // error handling here
        }
        System.out.println(GameManager.isOnline);
        System.out.println("handle Message");
    }

    public void handleSetColor(String s) {
        String color = s.split(";")[2];
        GameManager.gameLogic.COLOR = color;
        String message = API.Type.ROLL_DICE.toString() + ";" + color;
        broadcastMessage(message);
    }

    public void handleChooseCover(String s) {
        System.out.println("handleChooseCover");

        String[] splString = s.split(";");
        String message = API.Type.CHOOSE_COVER.toString();
        String column = splString[2];
        String row = splString[3];
        String color = GameManager.gameLogic.COLOR;
        int index = Coordinate.convertToIndex(new Coordinate(Integer.parseInt(column), Integer.parseInt(row)));
        GameManager.gameLogic.coverHashMap.put(color, index);
        message = message + ";" + column + ";" + row + ";" + color;
        broadcastMessage(message);
    }

    public void handleRolldice() {
        String msgClient = API.Type.ROLL_DICE + ";";
        String result = Dice.rollDice();
        GameManager.gameLogic.COLOR = result;
        msgClient = msgClient + result;
        broadcastMessage(msgClient);
    }

    public void handlePopUp(String s) {
        String status = s.split(";")[2];
        String message = API.Type.POP_UP.toString() + ";" + status;
        if (status.equals("right")) {
            boolean isGameOver = GameManager.updateGameOnline();
            String scoreInfo = GameManager.playerManager.PLAYER1.getName() + ";"
                    + GameManager.playerManager.PLAYER1.getScore() + ";"
                    + GameManager.playerManager.PLAYER2.getName() + ";" + GameManager.playerManager.PLAYER2.getScore();
            if (isGameOver) {
                handleEndGame(scoreInfo);
                return;
            } else {
                message = message + ";" + scoreInfo;
            }
        }
        broadcastMessage(message);
    }

    public void handleEndGame(String s) {
        String message = API.Type.END_GAME.toString() + ";somebsmes;" + s;
        System.out.println(message);
        broadcastMessage(message);
    }

    public void handleAnswer(String s) {
        String clientAnswer = s.split(";")[2];
        String serverAnswer = GameManager.getAnswer();
        String imageAnswer = GameManager.getCardImage();
        String msgClient = API.Type.ANSWER + ";";
        if (!clientAnswer.equals(serverAnswer)) {
            msgClient = msgClient + "wrong;" + clientAnswer + ";" + serverAnswer + ";" + imageAnswer;
            broadcastMessage(msgClient);
            GameManager.playerManager.changeTurn();
            // change turn
            return;
        }
        msgClient = msgClient + "right;" + clientAnswer;
        broadcastMessage(msgClient);
    }

    public void handleEnterProfile(String s) throws NumberFormatException {
        String[] splStrings = s.split(";");

        if (clientHandlers.size() == 1 || GameManager.playerManager.PLAYER1 == null) {
            System.out.println("helo");
            GameManager.playerManager.PLAYER1 = new Player(splStrings[1], Integer.parseInt(splStrings[2]));
            String msg = Type.ENTER_PROFILE.toString();
            unicastMessage(msg, 0);
            System.out.println(msg);
            return;
        }
        GameManager.playerManager.PLAYER2 = new Player(splStrings[1], Integer.parseInt(splStrings[2]));
        String msgToPlayer1 = Type.ENTER_PROFILE.toString() + ";"
                + GameManager.playerManager.PLAYER2.getName() + ";" + GameManager.playerManager.PLAYER2.getAge();
        String msgToPlayer2 = Type.ENTER_PROFILE.toString() + ";"
                + GameManager.playerManager.PLAYER1.getName() + ";" + GameManager.playerManager.PLAYER1.getAge();
        unicastMessage(msgToPlayer1, 0);
        unicastMessage(msgToPlayer2, 1);
        generateBoardGame();
        generateCover();
        announceTurn(true);
    }

    public void handleTurn() {
        announceTurn(false);
    }

    public void unicastMessage(String messageFromClient, int clientNumber) {
        // send message to specific machine
        System.out.println("messageFromClient");
        try {
            clientHandlers.get(clientNumber).bufferedWriter.write(messageFromClient);
            clientHandlers.get(clientNumber).bufferedWriter.newLine();
            clientHandlers.get(clientNumber).bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    }

    public void handleCloseConnection() {
        closeEverything(socket, bufferedReader, bufferedWriter);
    }

    public void generateCover() {
        Coordinate[] result = GameManager.gameLogic.setUpCover();
        Helper.generateCoverHashMap(result);
        String msgClient = API.Type.DATA.toString() + ";" + "cover;";
        for (int i = 0; i < Dice.numDice; i++) {
            msgClient = msgClient + result[i].getColumn() + ";";
            msgClient = msgClient + result[i].getRow() + ";";
        }
        broadcastMessage(msgClient);
    }

    public void broadcastMessage(String messageFromClient) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.bufferedWriter.write(messageFromClient);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
            }
        }
    }

    public void generateBoardGame() {
        String msgToClient = API.Type.DATA.toString() + ";" + "boardgame;";
        msgToClient = msgToClient + Dice.numDice + ";";
        msgToClient = msgToClient + GameManager.gameLogic.theme + ";";
        msgToClient = msgToClient + GameManager.countDownTimer + ";";
        for (Disc disc : GameManager.gameLogic.myList) {
            msgToClient = msgToClient + disc.getValue() + ";";
            msgToClient = msgToClient + disc.getCardImage() + ";";
        }
        System.out.println(msgToClient);
        broadcastMessage(msgToClient);
    }

    public void announceTurn(boolean isStart) {
        String msgToClient = API.Type.DATA.toString() + ";turn;";
        // set isPlayer1Turn
        // if isPlayer1Turn = true -> player 1
        // if isPlayer1Turn = false -> player 2
        if (isStart) {
            GameManager.playerManager.getFirstTurn();
        } else {
            GameManager.playerManager.changeTurn();
        }
        if (GameManager.playerManager.checkIsPlayer1Turn()) {
            msgToClient = msgToClient + GameManager.playerManager.PLAYER1.getName();
        } else {
            msgToClient = msgToClient + GameManager.playerManager.PLAYER2.getName();
        }
        broadcastMessage(msgToClient);
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: client num" + clientNumber + " has left the chat!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        if (clientNumber == 0)
            GameManager.playerManager.PLAYER1 = null;
        else
            GameManager.playerManager.PLAYER2 = null;
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
