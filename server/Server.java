package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private List<ClientHandler> peers;

    Server(){
        AuthService authService = new AuthServiceImpl();
        this.peers = new CopyOnWriteArrayList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;


        try {
            authService.connect();
            serverSocket = new ServerSocket(8181);
            System.out.println("Сервер запущен!");

            while (true){
                socket = serverSocket.accept();
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
                e.printStackTrace();
            }
        }
        authService.disconnect();
    }


    void subscribe(ClientHandler clientHandler) { this.peers.add(clientHandler);}


    void broadcast(String str) {
        Iterator var2 = this.peers.iterator();

        while(var2.hasNext()) {
            ClientHandler clientHandler = (ClientHandler)var2.next();
            clientHandler.sendMsg(str);
        }
    }

    public void unsubscribe(ClientHandler clientHandler) {
        this.peers.remove(clientHandler);
    }
}
