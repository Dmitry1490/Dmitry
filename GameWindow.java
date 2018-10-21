import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_POS_X = 650;
    private static final int WINDOW_POS_Y = 250;

    private Map map;
    private SettingsWindow settingsWindow;

    GameWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//закрыли окно - закрыли программу;
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X,WINDOW_POS_Y);
        setResizable(false);// изменять размер окна true/false;
        setTitle("TicTacToe");//название окна;
        JButton jbtStart = new JButton("Start");
        jbtStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        });
        JButton jbtStop = new JButton("Stop");
        jbtStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1,2));// Layout - расположение/палинровка - компановщик;
        panelButton.add(jbtStart); // добавили кнопку на панель;
        panelButton.add(jbtStop); // добавили кнопку на панель;


        map = new Map();
        settingsWindow = new SettingsWindow(this);

        add(map, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        setVisible(true);// видимость окна;


    }

    void gameParamtr(int mode, int fieldSizwX, int fieldSizwY, int winLenght){
        map.gameParamtr(mode, fieldSizwX, fieldSizwY, winLenght);
    }

}
