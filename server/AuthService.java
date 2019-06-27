package server;

public interface AuthService {

    String getNick(String value1, String value2);

    void connect();

    void disconnect();

}
