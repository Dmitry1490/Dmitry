package server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientHandler {

    private static List<String> invalidWords = null;
    private static boolean invalid = false;
    private Socket socket;
    private Server server;
    private AuthService authService;
    private DataOutputStream out;
    private DataInputStream in;
    private ExecutorService executorService;
    private File file = new File("history.txt");
    private String log4jConfPath = "D:\\скачки\\Java_lvl3_lesson6_logNoMaven\\log4j.properties";
    private static final Logger log = Logger.getLogger(Server.class);


    ClientHandler(Server server, Socket socket) {
        PropertyConfigurator.configure(log4jConfPath);
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
                   log.error(e);
                } finally {
                    close();
                }
            });
        } catch (IOException e) {
            log.error(e);
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
                invalidFilter(str);
                server.broadcast(str);
                writeUsingHistory(str, file);

            } catch (IOException e) {
                log.error(e);
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
            log.error(e);
        }
        server.unsubscribe(this);
    }

    void sendMsg(String message) {
        try {
            if (!invalid){
            out.writeUTF(message);}
            else{
                out.writeUTF("Не ругайтесь!");
                invalid = false;
            }
        } catch (IOException e) {
            log.error(e);
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
                log.error(e);
            } finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    log.error(e);
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
            log.error(e);
        }
    }
    // Фильтр модератор некорректных слов;
    private void invalidFilter(String str){
        addInvalidWord();
        String[] tokens = str.split(" ");
        for (String s: invalidWords) {
            for (int i = 0; i < tokens.length ; i++) {
                if(s.equals(tokens[i].trim())){
                    invalid = true;
                }
            }
        }
    }

    private void addInvalidWord(){
        invalidWords = new ArrayList();
        invalidWords.add("попа");
        invalidWords.add("блин");
    }

}
