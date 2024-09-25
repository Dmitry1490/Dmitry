import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    BufferedReader reader;


    public Server() {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Сервер запущен, ждет подключения ...");
            socket = serverSocket.accept(); // подслушиваем порт 8888;
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                String txt = null;
                try {
                    txt = in.readUTF();
                    if (txt.equalsIgnoreCase("end")) {
                        System.out.println("Сервер отключился");
                        // Но клиент в итоге не отключается, хотя сюда заходит когда end;
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (txt != null) {
                    try {
                        sendMessage(txt);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            try {
                writeToConsole();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



    void sendMessage(String msg) throws IOException {
        if (msg.equalsIgnoreCase("end"));
        out.writeUTF(msg);
        System.out.println(msg);
    }

    void writeToConsole() throws IOException {
        while (true) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String txt = reader.readLine();
            sendMessage(txt);
        }
    }


    public static void main(String[] args) {

        Server server = new Server();
    }

}
