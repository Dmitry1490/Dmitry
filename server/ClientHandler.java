package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientHandler {

    private Socket socket;
    private Server server;
    private AuthService authService;
    private DataOutputStream out;
    private DataInputStream in;

    ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.authService = new AuthServiceImpl();
            new Thread(() -> {
                try {
                    autorization();
                    read();
                    changeNik();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        while (true) {
            try {
                String str = in.readUTF();
                if (str.equalsIgnoreCase("/end")) {
                    sendMsg("/serverclosed");
                    break;
                }
                server.broadcast(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // /auth login1 password1
    private void autorization() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] tokens = str.split(" ");
                String nick = authService.getNick(tokens[1], tokens[2]);
                if (nick != null) {
                    sendMsg("/authOK");
                    server.subscribe(this);
                    break;
                } else {
                    sendMsg("Неверный логин/пароль");
                }
            }
        }
    }

    private void changeNik() throws IOException{
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/change")) {
                String[] tokens = str.split(" ");
                String nick = authService.getNickByLogin(tokens[1], tokens[2]);
                if (nick != null) {
                    sendMsg("/changeOK your new nick - " + nick);
                    break;
                } else {
                    sendMsg("Неверный логин/пароль");
                }
            }
        }

    }


    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.unsubscribe(this);
    }

    void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
