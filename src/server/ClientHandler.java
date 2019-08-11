package server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientHandler {

    private Socket socket;
    private Server server;
    private AuthService authService;
    private DataOutputStream out;
    private DataInputStream in;
    private ExecutorService executorService;
    private File file = new File("history.txt");


    ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.authService = new AuthServiceImpl();
            this.executorService = Executors.newFixedThreadPool(1);

    // 2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.

            executorService.execute(() -> {
                try {
                    autorization();
                    returHistory();
                    read();
                    changeNik();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            });
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
                writeUsingHistory(str, file);
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
            executorService.shutdown();
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

    // запись истории сообщений в history.txt;
    private void writeUsingHistory(String str, File file) {
            FileWriter fr = null;
            final String sp = System.getProperty("line.separator");
            try {
                fr = new FileWriter(file, true);
                fr.write(str + sp);
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    // печать истории после авторизации;
    private void returHistory(){
        FileReader fr = null;
        char [] a = new char[2000];
        try {
            fr = new FileReader(file);
            fr.read(a);
            String text = String.valueOf(a);
            out.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
