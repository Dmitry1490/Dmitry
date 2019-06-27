package client;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

public class Controller_fx {
    @FXML
    HBox AuthPanel;
    @FXML
    TextField LoginField;
    @FXML
    PasswordField PasswordField;
    @FXML
    TextArea chatArea;
    @FXML
    HBox EnterMEssenge;
    @FXML
    TextField msgField;


    final String ADDRESS = "localhost";
    final int PORT = 8181;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    private boolean isAutorized;

    public Controller_fx() {
    }

    public void ClientConnect(){
        try {
            this.socket = new Socket("localhost", 8181);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
            (new Thread(() -> {
                try {
                    this.auth();
                    this.read();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    this.close();
                }
            })).start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setAutorized(boolean isAutorized) {
        this.isAutorized = isAutorized;
        if (!isAutorized) {
            this.AuthPanel.setVisible(true);
            this.AuthPanel.setManaged(true);
            this.EnterMEssenge.setVisible(false);
            this.EnterMEssenge.setManaged(true);
        } else {
            this.AuthPanel.setVisible(false);
            this.AuthPanel.setManaged(false);
            this.EnterMEssenge.setVisible(true);
            this.EnterMEssenge.setManaged(true);
        }

    }

    private void auth() throws IOException {
        while(true){
            String str = this.in.readUTF();
            if (str.startsWith("/authOK")){
                this.setAutorized(true);
                return;
            }

        }
    }

    private void read() throws IOException {
        while (true){
            String str = this.in.readUTF();
            if(str.equalsIgnoreCase("/serverclosed")){
                return;
            }
         this.chatArea.appendText(str + "\n");
        }
    }

    private void close(){
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public void tryToAuth(){
        try {
            this.out.writeUTF("/auth " + LoginField.getText() + " " + PasswordField.getText());
            this.LoginField.clear();
            this.PasswordField.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void SendMsg(){
        try {
            this.out.writeUTF(this.msgField.getText());
            this.msgField.clear();
            this.msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
