package ws2022.Server;

public class ServerThread implements Runnable {

    public synchronized void requestStop() {
        server.closeServerSocket();
    }

    private Server server;

    public ServerThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        server.startServer();
    }
}
