import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String ADDRESS = "localhost";
    private final int port = 8888;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private BufferedReader reader;

    private String clientMsng;
    private String serverMsng;

    public Client(){

        try {
            openConnection();
        } catch (IOException e) {
            System.out.println("error connection ...");
            e.printStackTrace();
        }

        new Thread(() ->{
            try {
                readMsng();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() ->{
            try {
                sendMsng();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


    }

    private void openConnection() throws IOException {
        System.out.println("Open connect");
        socket = new Socket(ADDRESS, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void readMsng() throws IOException {
        while (true){
            serverMsng = in.readUTF();
            System.out.println(serverMsng);
            if(serverMsng.equals("end"))
            {
                closeConnection();
                break;
            };
        }


    }

    private void sendMsng() throws IOException {
        while (true){
            if((clientMsng = reader.readLine()) != null){
                out.writeUTF(clientMsng);
                if (clientMsng.equalsIgnoreCase("end"))
                {
                    closeConnection();
                    break;
                }
            }
        }
    }

    private void closeConnection() {
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
    }



    public static void main(String[] args) {
        Client client = new Client();
    }


}

