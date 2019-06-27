package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Обработчик клиентов;
public class ClientHandler {
    
    private Socket socket;
    private Server server;
    private AuthService authService;
    private DataOutputStream out;
    private DataInputStream in;
    
    ClientHandler(Server server, Socket socket){
        
        
        try {
            
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.authService = new AuthServiceImpl();
            
            (new Thread(() -> {

                try {
                    this.autorization();
                    this.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            })).start();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    private void autorization() throws IOException {
        while(true){
            String str = this.in.readUTF();
            if(str.startsWith("/auth")){
                String[] tokens = str.split(" ");
                String nike = this.authService.getNick(tokens[1], tokens[2]);
                if(nike != null){
                    this.sendMsg("/authOK");
                    this.server.subscribe(this);
                    return;
                }

                this.sendMsg("Wrong login or password");
            }
        }
    }

    void sendMsg(String s) {
        try {
            this.out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        while(true) {
            try {
                String str = this.in.readUTF();
                if (str.equalsIgnoreCase("/end")) {
                    this.sendMsg("/serverclosed");
                    return;
                }

                this.server.broadcast(str);
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    private void close() {
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.server.unsubscribe(this);
    }

}

