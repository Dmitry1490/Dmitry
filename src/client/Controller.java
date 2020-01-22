package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    TextField msgField;
    @FXML
    TextArea chatArea;
    @FXML
    HBox bottomPanel;
    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    HBox transformPanelButton;
    @FXML
    HBox yourLogin;
    @FXML
    TextField takeLogin;
    @FXML
    HBox changeNik;
    @FXML
    Text textSafeOrCancel;
    @FXML
    TextField newNik;
    @FXML
    HBox safePassAndLoginOrCancel;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String ADDRESS = "localhost";
    final int PORT = 8181;

    private boolean isAAutorized;
    private boolean isChange;

    private void setAutorized(boolean isAAutorized) {
        this.isAAutorized = isAAutorized;
        if (!isAAutorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(true);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            transformPanelButton.setVisible(true);
        }
    }

    private void setChangePanel(boolean isChange){
        if(isChange){
            chatArea.setVisible(false);
            bottomPanel.setVisible(false);
            yourLogin.setVisible(true);
            changeNik.setVisible(true);
            textSafeOrCancel.setVisible(true);
            safePassAndLoginOrCancel.setVisible(true);
        } else{
            yourLogin.setVisible(false);
            changeNik.setVisible(false);
            textSafeOrCancel.setVisible(false);
            safePassAndLoginOrCancel.setVisible(false);
            chatArea.setVisible(true);
            bottomPanel.setVisible(true);
            transformPanelButton.setVisible(true);
        }
    }

    private void connect() {
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    auth();
                    read();
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

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.equalsIgnoreCase("/serverclosed")) {
                break;
            }
            chatArea.appendText(str + "\n");
        }
    }

    private void auth() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/authOK")) {
                setAutorized(true);
                break;
            } else {
                chatArea.appendText(str + "\n");
            }
        }
    }

    public void tryToAuth() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePasswordAndLogin() {
        setChangePanel(true);
    }

    public void cancel() {
        setChangePanel(false);
    }


    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void safe() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/change " + takeLogin.getText() + " " + newNik.getText());
            takeLogin.clear();
            newNik.clear();
            setChangePanel(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
