package ws2022.Server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * This class represents a server that listens to incoming client connections
 * and starts a new thread for each connected client.
 * 
 * @author
 * 
 * @version 1.0
 * 
 */
public class Server {
    private ServerSocket serverSocket;

    /**
     * 
     * Constructs a new Server instance with the given ServerSocket
     * 
     * @param serverSocket the ServerSocket instance that the server will use to
     *                     listen for incoming connections
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * 
     * Starts the server by listening for incoming client connections and starting a
     * new thread for each client.
     */
    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    /**
     * 
     * Closes the server socket
     */
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
                System.out.print("close server");
            } else {
                System.out.println("no serversocet");
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 
     * Executes the server by creating a new Server instance, starting the server,
     * and returning the Server instance.
     * 
     * @return the Server instance
     * @throws IOException if an I/O error occurs while creating the ServerSocket
     */
    public static Server execute() throws IOException {
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
        ServerSocket serverSocket = new ServerSocket(1809);
        Server server = new Server(serverSocket);
        server.startServer();
        return server;
    }
}
