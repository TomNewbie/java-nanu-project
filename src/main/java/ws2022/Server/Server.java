package ws2022.Server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.ServerSocket;
import java.net.Socket;

import ws2022.Client.Model.GameManager;

public class Server {
    private ServerSocket serverSocket;

    Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            int count = 0;
            while (!serverSocket.isClosed()) {
                count++;
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Your IPv4 address: ");
        // Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
        ServerSocket serverSocket = new ServerSocket(1809);
        Server server = new Server(serverSocket);
        GameManager.startGame();
        server.startServer();
    }
}
