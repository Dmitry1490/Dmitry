package server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class StartServer {

    public static void main(String[] args) {
        new Server();
    }
}

class Server {

    private List<ClientHandler> peers;
    private String log4jConfPath = "D:\\скачки\\Java_lvl3_lesson6_logNoMaven\\log4j.properties";
    private static final Logger log = Logger.getLogger(Server.class);

    Server() {
        PropertyConfigurator.configure(log4jConfPath);
        AuthService authService = new AuthServiceImpl();
        peers = new CopyOnWriteArrayList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            authService.connect();
            serverSocket = new ServerSocket(8181);
            log.info("Сервер запущен");
            System.out.println("Сервер запущен");
            while (true) {
                socket = serverSocket.accept();
                log.info("Клиент подключился!");
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                log.error(e);
            }
            authService.disconnect();
        }
    }

    void broadcast(String message) {
        for (ClientHandler clientHandler : peers) {
            clientHandler.sendMsg(message);
        }
    }

    void subscribe(ClientHandler clientHandler) {
        peers.add(clientHandler);
    }

    void unsubscribe(ClientHandler clientHandler) {
        peers.remove(clientHandler);
    }
}
